import java.sql.{Connection, PreparedStatement, ResultSet, Timestamp}
import java.text.SimpleDateFormat
import Database._

case class Room(id: Int, number: Int, capacity: Int, price: BigDecimal, description: String, status: String)
case class User(id: Int, name: String, cpf: String, address: String)
case class Reservation(id: Int, userId: Int, roomId: Int, startDate: String, endDate: String)

class HotelBookingSystem {
  val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def bookReservation(reservation: Reservation): Boolean = {
    val conn: Connection = getConnection()
    try {
      val query = "INSERT INTO reservations (user_id, room_id, start_date, end_date) VALUES (?, ?, ?, ?)"
      val preparedStatement: PreparedStatement = conn.prepareStatement(query)
      preparedStatement.setInt(1, reservation.userId)
      preparedStatement.setInt(2, reservation.roomId)
      preparedStatement.setTimestamp(3, new Timestamp(dateFormat.parse(reservation.startDate).getTime))
      preparedStatement.setTimestamp(4, new Timestamp(dateFormat.parse(reservation.endDate).getTime))

      val rowsInserted = preparedStatement.executeUpdate()
      rowsInserted > 0
    } catch {
      case e: Exception =>
        println("Erro ao reservar quarto: " + e.getMessage)
        false
    } finally {
      conn.close()
    }
  }
}