import org.mindrot.jbcrypt.BCrypt

object SecurityUtils {
  def hashPassword(password: String): String = {
    BCrypt.hashpw(password, BCrypt.gensalt())
  }

  def checkPassword(plainPassword: String, hashedPassword: String): Boolean = {
    BCrypt.checkpw(plainPassword, hashedPassword)
  }
}
