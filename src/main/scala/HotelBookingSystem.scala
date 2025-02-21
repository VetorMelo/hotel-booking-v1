import java.sql.{Connection, Date, PreparedStatement, ResultSet}
import java.text.SimpleDateFormat
import org.slf4j.LoggerFactory
import Database._

case class Room(id: Int, capacity: Int, status: String)
case class Reservation(id: Int, roomId: Int, guestName: String, startDate: String, endDate: String)

class HotelBookingSystem {
  private val logger = LoggerFactory.getLogger(getClass)

  def addRoom(room: Room): Unit = {
    val conn: Connection = getConnection()
    val query = "INSERT INTO rooms (capacity, status) VALUES (?, ?)"
    try {
      val preparedStatement: PreparedStatement = conn.prepareStatement(query)
      preparedStatement.setInt(1, room.capacity)
      preparedStatement.setString(2, room.status)
      preparedStatement.executeUpdate()
      logger.info(s"Quarto inserido com sucesso: $room")
    } catch {
      case e: Exception =>
        logger.error("Erro ao inserir o quarto:", e)
    } finally {
      conn.close()
    }
  }

  def bookReservation(reservation: Reservation): Boolean = {
    val conn: Connection = getConnection()
    val query = "INSERT INTO reservations (room_id, guest_name, start_date, end_date) VALUES (?, ?, ?, ?)"
    try {
      val preparedStatement: PreparedStatement = conn.prepareStatement(query)

      // Conversão das datas de String para java.sql.Date
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
      val startDate = new Date(dateFormat.parse(reservation.startDate).getTime)
      val endDate = new Date(dateFormat.parse(reservation.endDate).getTime)

      preparedStatement.setInt(1, reservation.roomId)
      preparedStatement.setString(2, reservation.guestName)
      preparedStatement.setDate(3, startDate)
      preparedStatement.setDate(4, endDate)

      val rowsInserted = preparedStatement.executeUpdate()
      conn.close()

      if (rowsInserted > 0) {
        logger.info(s"Reserva realizada com sucesso: $reservation")
        true
      } else {
        logger.warn(s"Falha ao criar a reserva para: $reservation")
        false
      }
    } catch {
      case e: Exception =>
        logger.error("Erro ao criar a reserva:", e)
        false
    } finally {
      conn.close()
    }
  }

  def isRoomAvailable(roomId: Int, startDate: String, endDate: String): Boolean = {
    val conn: Connection = getConnection()
    val query = "SELECT * FROM reservations WHERE room_id = ? AND (start_date < ? AND end_date > ?)"
    try {
      val preparedStatement: PreparedStatement = conn.prepareStatement(query)

      // Conversão das datas de String para java.sql.Date
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
      val startSqlDate = new Date(dateFormat.parse(startDate).getTime)
      val endSqlDate = new Date(dateFormat.parse(endDate).getTime)

      preparedStatement.setInt(1, roomId)
      preparedStatement.setDate(2, endSqlDate)
      preparedStatement.setDate(3, startSqlDate)

      val resultSet: ResultSet = preparedStatement.executeQuery()
      val isAvailable = !resultSet.next()

      if (!isAvailable) {
        logger.warn(s"Quarto $roomId não está disponível de $startDate a $endDate")
      }
      isAvailable
    } catch {
      case e: Exception =>
        logger.error("Erro ao verificar disponibilidade do quarto:", e)
        false
    } finally {
      conn.close()
    }
  }
}
