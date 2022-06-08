package util

import com.github.tminglei.slickpg.{ExPostgresProfile, PgArraySupport, PgDate2Support, PgHStoreSupport, PgNetSupport, PgPlayJsonSupport, PgRangeSupport, PgSearchSupport, utils}
import play.api.libs.json.{JsValue, Json}
import slick.basic.Capability
import slick.jdbc.{JdbcCapabilities, JdbcType}

import java.time.ZoneId

trait PgProfile extends ExPostgresProfile
  with PgArraySupport
  with PgDate2Support
  with PgRangeSupport
  with PgHStoreSupport
  with PgPlayJsonSupport
  with PgNetSupport
  with PgSearchSupport {
  override def pgjson: String = "jsonb"

  // Add back `capabilities.insertOrUpdate` to enable native `upsert` support; for postgres 9.5+
  override protected def computeCapabilities: Set[Capability] =
    super.computeCapabilities + JdbcCapabilities.insertOrUpdate

  override val api: MyAPI = new MyAPI {}

  trait MyAPI extends API
    with ArrayImplicits
    with DateTimeImplicits
    with JsonImplicits
    with RangeImplicits
    with HStoreImplicits
    with NetImplicits
    with SearchImplicits
    with SearchAssistants {


    override implicit val date2ZoneIdMapper: JdbcType[ZoneId] = new GenericJdbcType[ZoneId]("text", ZoneId.of(_), _.getId, hasLiteralForm=false)
    implicit val strListTypeMapper: DriverJdbcType[List[String]] = new SimpleArrayJdbcType[String]("text").to(_.toList)
    implicit val playJsonArrayTypeMapper: DriverJdbcType[List[JsValue]] =
      new AdvancedArrayJdbcType[JsValue](pgjson,
        s => utils.SimpleArrayUtils.fromString[JsValue](Json.parse)(s).orNull,
        v => utils.SimpleArrayUtils.mkString[JsValue](_.toString())(v)
      ).to(_.toList)

  }

}

object PgProfile extends PgProfile
