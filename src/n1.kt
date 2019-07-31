import java.lang.NumberFormatException

fun main() {
    print("Hi, Welcome to XO game!\n")
    start()
}

var board = arrayOf(
    arrayOf(' ', '1', '2', '3'),
    arrayOf('1', '-', '-', '-'),
    arrayOf('2', '-', '-', '-'),
    arrayOf('3', '-', '-', '-')
)

data class PositionXO(val X:Int, val Y:Int)

fun input():PositionXO {
    var x=0
    var y=0
    while(true){
        println("Enter Row number!")
        var first = readLine()
        println("Enter Column number!")
        var second = readLine()
        try {
            x = Integer.valueOf(first)
            y = Integer.valueOf(second)
            if(x>=1 && x<=3 && y>=1 && y<=3 && board[x][y] == '-'){
                break
            }

            throw NumberFormatException("Wrong Range")
        }catch (ex: NumberFormatException){
            println("Please input only number! "+ex.message)
        }
    }
    return PositionXO(x,y)
}



fun showBoard() {
    for(i in board.indices){
        for(j in board[i].indices){
            if(board[i][j].toString() == "0"){
                print("- ")
                continue
            }
            print(board[i][j].toString()+" ")
        }
        println()
    }
}

fun init() {

}

fun findWinner() : Boolean {
    for(i in board.indices){
        if(board[i][1] == board[i][2] && board[i][2] == board[i][3] && !board[i].contains('-')){
            return true
        }

        if(board[1][i] == board[2][i] && board[2][i] == board[3][i] && board[3][i]!='-'){
            return true
        }

        if(board[1][1] == board[2][2] && board[2][2] == board[3][3] && board[2][2]!='-'){
            return true
        }

        if(board[1][3] == board[2][2] && board[2][2] == board[3][1] && board[2][2]!='-'){
            return true
        }
    }
    return false
}

fun start() {
    var turn = listOf('X','O')
    var turnNum = 0
    showBoard()
    while(true){
        println("Turn "+turn[turnNum%2])
        init()
        val res:PositionXO = input()
        board[res.X][res.Y] = turn.get(turnNum++%2)
        if(findWinner()){
            println("The Winner is "+turn[(turnNum%2)-1])
            print("Game Over!")
            break
        }

        showBoard()
    }

}