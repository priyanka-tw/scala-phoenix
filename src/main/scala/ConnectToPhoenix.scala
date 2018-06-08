import java.sql.DriverManager
import java.sql.Connection


object ConnectToPhoenix extends App {
  // connect to the database named "mysql" on the localhost
  val driver = "org.apache.phoenix.queryserver.client.Driver"
  val url = "jdbc:phoenix:thin:url=http://localhost:8765;serialization=PROTOBUF"

  // there's probably a better way to do this
  var connection: Connection = null

  try {
    // make the connection
    Class.forName(driver)
    connection = DriverManager.getConnection(url)

    print("After connection..")

    // create the statement, and run the select query
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("select * from javatest")
    while (resultSet.next()) {
      val host = resultSet.getInt(1)
      val user = resultSet.getString(2)
      println(s"Row = $host, $user")
    }
  } catch {
    case e => e.printStackTrace
  }
  connection.close()
}
