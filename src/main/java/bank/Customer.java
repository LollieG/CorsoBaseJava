package bank;

//ordine struttura 
// class {

// 1. variabili -> vedi cosa contiene la classe
// 2. costruttore
// 3. getter -> come leggere il contenuto
// 4. setter -> come modificare il contenuto
//}
//le classi vanno scritte con la prima lettera maiuscola
//public -> può essere usato fuori dalla classe
//int -> valore semplice perchè contiene un valore diretto = minuscolo
//String -> classe/oggetto = maiuscolo
//differenza fra int e integer -> integer può essere null

//creazione della classe ossia un "modello" e le sue variabili
public class Customer {
  // private-> modificabili solo all'interno della classe
  private int id;
  private String name;
  private String username;
  private String password;
  private int accountId;
  private boolean authenticated;

  // costruttore della classe, il nome deve essere lo stesso della
  // classe(=Customer)
  // -> un metodo per creare e inizializzare un oggetto/classe
  // in questo modo definisco quali dati devo ricevere
  public Customer(int id, String name, String username, String password, int accountId) {
    // il metodo set... salva i dati dentro ogni oggetto
    setId(id);
    setName(name);
    setUsername(username);
    setPassword(password);
    setAccountId(accountId);
    setAuthenticated(false);
  }

  // lettura dei dati
  public int getId() {
    return this.id;
  }

  // metodo che serve a salvare un valore dentro l'oggetto
  // void ->metodo che riceve un valore che modifica l'oggetto ma non restituisce
  // il risultato
  // setId -> nome del metodo
  // set -> necessario per salvare
  // Id -> campo da impostarae
  // (int id) -> valore che riceve
  public void setId(int id) {
    // this.id -> variabile dell'oggetto
    // id -> parametro in entrata del metodo
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAccountId() {
    return this.accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public boolean isAuthenticated() {
    return this.authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }

}
