
import java.sql.DriverManager
import java.sql.Connection


object ConnectToPhoenix {

  def main(args: Array[String]) {
    // connect to the database named "mysql" on the localhost
    val driver = "org.apache.phoenix.jdbc.PhoenixDriver"
    val url = "jdbc:phoenix:localhost"


    // there's probably a better way to do this
    var connection: Connection = null

    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url)

      print("After connection..")

      // create the statement, and run the select query
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT host, user FROM user")
      while (resultSet.next()) {
        val host = resultSet.getString("host")
        val user = resultSet.getString("user")
        println("host, user = " + host + ", " + user)
      }
    } catch {
      case e => e.printStackTrace
    }
    connection.close()
  }

}