import java.sql.{Connection, DriverManager}

object Database {
  val url = "jdbc:postgresql://localhost:5432/hotel_booking?ssl=false"
  val username = "postgres"
  val password = "Tw221301@"

  def getConnection(): Connection = {
    DriverManager.getConnection(url, username, password)
  }
}
