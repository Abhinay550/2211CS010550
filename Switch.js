let a = 10, b = 25, c = 15;
let greatest;

if (a > b && a > c) {
    greatest = 'a';
} else if (b > a && b > c) {
    greatest = 'b';
} else {
    greatest = 'c';
}

switch (greatest) {
    case 'a':
        console.log("The greatest value is: " + a);
        break;
    case 'b':
        console.log("The greatest value is: " + b);
        break;
    case 'c':
        console.log("The greatest value is: " + c);
        break;
    default:
        console.log("Values are equal or invalid input.");
}
