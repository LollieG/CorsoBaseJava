package bank; // Definisce il package in cui si trova questa classe

// Import delle classi necessarie per lavorare con il database SQL
import java.sql.Connection;         // Rappresenta una connessione al database
import java.sql.DriverManager;      // Serve per aprire una connessione al database
import java.sql.PreparedStatement;  // Permette di eseguire query SQL con parametri
import java.sql.ResultSet;          // Contiene i risultati di una query
import java.sql.SQLException;       // Gestisce errori legati al database

// Definizione della classe DataSource
public class DataSource {
  // Metodo che crea e retituisce la conessione al database
  public static Connection connect() {

    // Stringa di connessione al database SQLite
    String db_file = "jdbc:sqlite:resources/bank.db"; // jdbc:sqlite: -> serve a java per capire che sto usando SQLite
                                                      // resources/bank.db -> percorso db
    
    // Variabile che conterrà la connessione
    Connection connection = null;
    
    try{
      // DriveManager prova una connessione con il db
      connection = DriverManager.getConnection(db_file);

      // Messaggio di conferma se la connessione è riuscita
      System.out.println( "we're connected");
      
    }  catch(SQLException e){ // gestione dell errore
      // Se c'è un errore stampa i dettagli
      e.printStackTrace();
    }

    //restituisce la connessione (può essere null se fallisce)
    return connection;
  }

  // Metodo che recupera un cliente dal database dato lo username
  public static Customer getCustomer(String username) {

    // Query SQL con parametro (?) 
    String sql = "select * from customers where username = ?";

    // Variabile che conterrà il cliente trovato
    Customer customer = null;

    // try-with-resources: chiude automaticamente connection e statement
    try(Connection connection = connect(); // apre connessione
      PreparedStatement statement = connection.prepareStatement(sql) // prepara query
    ){

      // Imposta il valore del parametro (?) nella query (posizione 1)
        statement.setString(1, username);

        // Esegue la query e ottiene i risultati
        try (ResultSet resultSet = statement.executeQuery()){

          // Crea un oggetto Customer usando i dati ottenuti dal database
          customer = new Customer(
            resultSet.getInt("id"),           // legge la colonna "id"       
            resultSet.getString("name"),      // legge "name"
            resultSet.getString("username"),  // legge "username"
            resultSet.getString("password"),  // legge "password"
            resultSet.getInt("account_Id")    // legge "account_Id"
          );  
        }
    }catch(SQLException e){
      // Gestione errori SQL
      e.printStackTrace();
    }

    // Restituisce il cliente (null se non trovato o errore)
    return customer;
  }
  
  // Metodo che recupera account
  public static Account getAccount(int accountId) {
    String sql = "select * from accounts where id = ?";
    Account account = null;
    try(Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)){
          statement.setInt(1, accountId);

          try(ResultSet resultSet = statement.executeQuery()){
            account = new Account(
              resultSet.getInt("id"),
              resultSet.getString("type"),
              resultSet.getDouble("balance"));
            }
        }catch(SQLException e) {
          e.printStackTrace();
        }
          return account;
  }
  // Metodo main: punto di ingresso del programma, altrimenti non si può avviare
    public static void main(String[] args){ // String[] -> array di stringhe
                                            // args -> nome variabile
                                                // in questo caso args non viene utilizzato direttamente
                                                // viene valorizzato quando lancio il comando dal terminale
                                                // ex. java Main ciao mondo -> args[0] = "ciao" args[1]="mondo"
                                                // devo comunque scriverlo nel pgm altrimenti java non riconosce il comando
      
      // Chiama il metodo getCustomer per cercare un utente
      Customer customer = getCustomer("twest8o@friendfeed.com");
      Account account = getAccount(customer.getAccountId());
      // Stampa il nome del cliente trovato
      System.out.println(account.getBalance());
  }

}
