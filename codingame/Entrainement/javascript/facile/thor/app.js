var inputs = readline().split(' ');
const lightX = parseInt(inputs[0]); // the X position of the light of power
const lightY = parseInt(inputs[1]); // the Y position of the light of power
const initialTx = parseInt(inputs[2]); // Thor's starting X position
const initialTy = parseInt(inputs[3]); // Thor's starting Y position
let actualX = initialTx 
let actualY = initialTy
let response = ""

// game loop
while (true) {
    const remainingTurns = parseInt(readline()); // The remaining amount of turns Thor can move. Do not remove this line.
    response = ""

    if( actualY < lightY) {
         response = "S"
         actualY++
    }else if ( actualY > lightY ){
        response = "N"
        actualY--
    } 

    if( actualX < lightX ) {
         response += "E"
         actualX++
    }else if ( actualX > lightX ){
        response += "W"
        actualX--
    }   

    // A single line providing the move to be made: N NE E SE S SW W or NW
    console.log(response);
}