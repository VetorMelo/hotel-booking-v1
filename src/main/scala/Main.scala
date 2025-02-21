object Main {
  def main(args: Array[String]): Unit = {
    println("Iniciando o sistema de reservas de hotel...")

    val system = new HotelBookingSystem()

    // Criando um exemplo de reserva para teste
    val reservaExemplo = Reservation(1, 1, 1, "2025-02-21 12:00:00", "2025-02-22 12:00:00")

    if (system.bookReservation(reservaExemplo)) {
      println("Reserva realizada com sucesso!")
    } else {
      println("Falha ao realizar reserva.")
    }
  }
}
