import java.sql.{Connection, DriverManager}

object Database {
  val url = "jdbc:postgresql://localhost:5432/hotel_booking"
  val username = "postgres"
  val password = "Tw221301@"

  def getConnection(): Connection = {
    try {
      DriverManager.getConnection(url, username, password)
    } catch {
      case e: Exception =>
        println("Erro na conexão com o banco de dados: " + e.getMessage)
        throw e
    }
  }
}