//*********************************************************************************** */
//              https://www.codingame.com/training/easy/the-descent
//*********************************************************************************** */

// game loop
while (true) {

    let maxHeight = 0;
    let indexMaxHeight = 0;

    for (let i = 0; i < 8; i++) {
        const mountainH = parseInt(readline()); // represents the height of one mountain.

       if  (maxHeight < mountainH){
           maxHeight = mountainH
           indexMaxHeight = i
       }
    }

    console.log(indexMaxHeight);     // The index of the mountain to fire on.
}