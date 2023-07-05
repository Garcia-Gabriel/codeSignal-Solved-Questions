import java.math.BigDecimal

fun main() {
	class Client (
	private var timestamp: Int,
	private var accountId: String
) {
	var clientAccountId = accountId
	var amount = BigDecimal.ZERO
}

fun solution(queries: MutableList<MutableList<String>>): MutableList<String> {
	var clients = hashMapOf<String, Client>()
	var deposits = hashMapOf<Int, BigDecimal>()
	var transfers = hashMapOf<Int, BigDecimal>()
	var results = mutableListOf<String>()

	fun CREATE_ACCOUNT(timestamp: Int, accountId: String): Boolean{
		var status = false
		if(!clients.containsKey(accountId)){
			val client: Client = Client(timestamp.toInt(), accountId)
			clients.put(client.clientAccountId, client)
			status = true
		} 
		return status 
	}

	fun DEPOSIT(timestamp: Int, accountId: String, amount: BigDecimal): String{
		var newAmount = ""
		if(clients.containsKey(accountId)){
			val client = clients.get(accountId)
			client!!.amount += amount
			newAmount = client!!.amount.toString()
			deposits.put(timestamp, amount)
		}
		return newAmount   
	}

	fun TRANSFER(timestamp: Int, sourceAccountId: String, targetAccountId: String, amount: BigDecimal): String{
		var senderAmount = ""
		if(clients.containsKey(sourceAccountId) && clients.containsKey(targetAccountId)){
			val sender = clients.get(sourceAccountId)
			val receiver = clients.get(targetAccountId)

			if(sender!!.amount >= amount){
				sender.amount -= amount
				receiver!!.amount += amount
				senderAmount = sender.amount.toString()
				transfers.put(timestamp, amount)
			}        
		}
		return senderAmount             
	}

	for(indice in queries.indices){
		if(queries[indice][0] == "CREATE_ACCOUNT"){
			results.add(CREATE_ACCOUNT(queries[indice][1].toInt(), queries[indice][2]).toString())
		} 
		else if(queries[indice][0] == "DEPOSIT"){
			results.add(DEPOSIT(queries[indice][1].toInt(), queries[indice][2], queries[indice][3].toBigDecimal()))
		} 
		else if((queries[indice][0] == "TRANSFER")){
			results.add(TRANSFER(queries[indice][1].toInt(), queries[indice][2], queries[indice][3], queries[indice][4].toBigDecimal()))
		}
	}         

	return results
}


/* Interview Question made by CodeSignal. The objective is to make a bank system that can register accounts, deposit money e transfer money to another account. 
The Input of the function is a mutable list of mutable lists of strings, and the output must be a mutable list of strings that contains the results of each function. */

// Rules: 	
// 1st. The solution function must have a mutable list of mutable lists of strings as input and a mutable list of strings as output.
// 2nd. If all the functions worked as expected, the respective output of the functions CREATE_ACCOUNT, DEPOSIT and TRANSFER, must be Boolean (True), String (client new amount) and String (sender new amount).
// 3rd. If the functions didn´t worked as expected, the respective output of the functions CREATE_ACCOUNT, DEPOSIT and TRANSFER, must be Boolean (False), Empty String ("") and Empty String("").

// Example:
var createAccount = mutableListOf("CREATE_ACCOUNT", "12", "Client 1") // True
var createAccount2 = mutableListOf("CREATE_ACCOUNT", "20", "Client 2") // True
var createAccount3 = mutableListOf("CREATE_ACCOUNT", "4", "Client 2") // False: because "Client 2" already exists
var deposit = mutableListOf("DEPOSIT", "9", "Client 1", "15000") // 15000 : 15K was deposited on first client´s account
var transfer = mutableListOf("TRANSFER", "15", "Client 1", "Client 2", "750") //14250 : 750 was transfered from client 1 to client 2
var queries: MutableList<MutableList<String>> = mutableListOf(createAccount, createAccount2, createAccount3, deposit, transfer)

var test = solution(queries)
println(test)

}
