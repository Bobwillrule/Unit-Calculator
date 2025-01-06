package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var name by remember{
                    mutableStateOf("")
                }
                var temp by remember{
                    mutableStateOf("")
                }
                var num by remember{
                    mutableStateOf("here")
                }
                var displayNum by remember{
                    mutableStateOf("changed")
                }
                var home by remember{
                    mutableStateOf(true)
                }
                var length by remember{
                    mutableStateOf(false)
                }
                var names by remember{
                    mutableStateOf(listOf<String>())
                }
                var answer = 0.0
                Column( // Main Column
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ){
                    LazyColumn {
                        items(names){
                                currentName->
                            Text(text = currentName,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clickable {
                                        num = currentName.substring(0..currentName.indexOf("=") - 1)
                                    }
                            )
                            Divider()
                        }
                    }
                    Row( // Answer
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()){
                        Text(text = displayNum,
                            fontSize = 60.sp)
                    }
                    Row( // switch keyboard
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()){
                        OutlinedButton(onClick = { home = false; length = true}, shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                            border = BorderStroke(1.dp, Color.White),
                            modifier = Modifier
                                .height(50.dp)
                                .width(180.dp)) {
                            Text(text = "Units")
                        }
                        OutlinedButton(onClick = { home = true; length = false }, shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                            border = BorderStroke(1.dp, Color.White),
                            modifier = Modifier
                                .height(50.dp)
                                .width(180.dp)) {
                            Text(text = "Numbers")
                        }
                    }
                    if (home) { // home
                        Row( //First row
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            inputButton(name = "7", input = "7", displayNum) { newNum -> displayNum = newNum;}
                            inputButton(name = "8", input = "8", num) { newNum -> num = newNum }
                            inputButton(name = "9", input = "9", num) { newNum -> num = newNum }
                            inputButton(name = "Del", input = num.substring(0..num.length - 1), num){ newNum -> num = newNum }
                            inputButton(name = "AC", input = "", num){ newNum -> num = "" }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween){

                        }
                        Row( //Second row
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            inputButton(name = "4", input = "4", num){ newNum -> num = newNum }
                            inputButton(name = "5", input = "5", num){ newNum -> num = newNum }
                            inputButton(name = "6", input = "6", num){ newNum -> num = newNum }
                            inputButton(name = "x", input = "x", num){ newNum -> num = newNum }
                            inputButton(name = "/", input = "/", num){ newNum -> num = newNum }
                        }
                        Row( //Third row
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            inputButton(name = "1", input = "1", num){ newNum -> num = newNum }
                            inputButton(name = "2", input = "2", num){ newNum -> num = newNum }
                            inputButton(name = "3", input = "3", num){ newNum -> num = newNum }
                            inputButton(name = "+", input = "+", num){ newNum -> num = newNum }
                            inputButton(name = "-", input = "-", num){ newNum -> num = newNum }
                        }
                        Row( //Fourth row
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            inputButton(name = "(", input = "(", num){ newNum -> num = newNum }
                            inputButton(name = "0", input = "0", num){ newNum -> num = newNum }
                            inputButton(name = ")", input = ")", num){ newNum -> num = newNum }
                            inputButton(name = ".", input = ".", num){ newNum -> num = newNum }
                            inputButton(name = "=", input = "9", num){ newNum -> num = entryPoint(num)
                            }
                        }
                    }
                    else {
                        // LENGTH CM STUFF
                        Row( //First row
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            inputButton(name = "nm", input = "nm1", num){ newNum -> num = newNum }
                            inputButton(name = "mm", input = "mm1", num){ newNum -> num = newNum }
                            inputButton(name = "cm", input = "cm1", num){ newNum -> num = newNum }
                            inputButton(name = "m", input = "m1", num){ newNum -> num = newNum }
                            inputButton(name = "km", input = "km1", num){ newNum -> num = newNum }
                        }
                        Row( //Second row
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            inputButton(name = "in", input = "in1", num){ newNum -> num = newNum }
                            inputButton(name = "ft", input = "ft1", num){ newNum -> num = newNum }
                            inputButton(name = "yd", input = "yd1", num){ newNum -> num = newNum }
                            inputButton(name = "mi", input = "mi1", num){ newNum -> num = newNum }
                            inputButton(name = "fur", input = "fur1", num){ newNum -> num = newNum }
                        }
                        Row( //Third row
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            inputButton(name = "4", input = "4", num){ newNum -> num = newNum }
                            inputButton(name = "5", input = "5", num){ newNum -> num = newNum }
                            inputButton(name = "6", input = "6", num){ newNum -> num = newNum }
                            inputButton(name = "x", input = "x", num){ newNum -> num = newNum }
                            inputButton(name = "/", input = "/", num){ newNum -> num = newNum }
                        }
                        Row( //Fourth row
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            inputButton(name = "4", input = "4", num){ newNum -> num = newNum }
                            inputButton(name = "5", input = "5", num){ newNum -> num = newNum }
                            inputButton(name = "6", input = "6", num){ newNum -> num = newNum }
                            inputButton(name = "x", input = "x", num){ newNum -> num = newNum }
                            inputButton(name = "/", input = "/", num){ newNum -> num = newNum }
                        }
                    }

                }
            }
        }
    }
}

fun calc(number: String): Double{
    println("In Calc input num: ${number}")
    var digits = mutableListOf<String>()
    var operators = mutableListOf<String>()
    var tempStr = ""
    val number = number.replace(" ", "").replace("(", "").replace(")", "")
    var answer = 0.000
    var tempInd = 0
    for (num in number) {
        if (num.isDigit() || num == '.'){
            tempStr += num
        } else{
            operators.add(num.toString())
            digits.add(tempStr)
            tempStr = ""
        }
    }
    digits.add(tempStr)
    while (!operators.isEmpty()){
        if (operators.contains("/") && operators.contains("x")){
            if (operators.indexOf("x") > operators.indexOf("/")){
                tempInd = operators.indexOf("/")
                answer = digits[tempInd].toDouble() / digits[tempInd + 1].toDouble()
                digits[tempInd] = answer.toString()
                digits.removeAt(tempInd+1)
                operators.removeAt(tempInd)
            } else{
                tempInd = operators.indexOf("x")
                answer = digits[tempInd].toDouble() * digits[tempInd + 1].toDouble()
                digits[tempInd] = answer.toString()
                digits.removeAt(tempInd+1)
                operators.removeAt(tempInd)
            }
        } else if (operators.contains("/")){
            tempInd = operators.indexOf("/")
            answer = digits[tempInd].toDouble() / digits[tempInd + 1].toDouble()
            digits[tempInd] = answer.toString()
            digits.removeAt(tempInd+1)
            operators.removeAt(tempInd)
        } else if (operators.contains("x")) {
            tempInd = operators.indexOf("x")
            answer = digits[tempInd].toDouble() * digits[tempInd + 1].toDouble()
            digits[tempInd] = answer.toString()
            digits.removeAt(tempInd+1)
            operators.removeAt(tempInd)
        } else {
            if (operators.contains("+") && operators.contains("-")){
                if (operators.indexOf("-") > operators.indexOf("+")){
                    tempInd = operators.indexOf("+")
                    answer = digits[tempInd].toDouble() + digits[tempInd + 1].toDouble()
                    digits[tempInd] = answer.toString()
                    digits.removeAt(tempInd+1)
                    operators.removeAt(tempInd)
                } else{
                    tempInd = operators.indexOf("-")
                    answer = digits[tempInd].toDouble() - digits[tempInd + 1].toDouble()
                    digits[tempInd] = answer.toString()
                    digits.removeAt(tempInd+1)
                    operators.removeAt(tempInd)
                }
            } else if (operators.contains("+")){
                tempInd = operators.indexOf("+")
                answer = digits[tempInd].toDouble() + digits[tempInd + 1].toDouble()
                digits[tempInd] = answer.toString()
                digits.removeAt(tempInd+1)
                operators.removeAt(tempInd)
            } else if (operators.contains("-")) {
                tempInd = operators.indexOf("-")
                answer = digits[tempInd].toDouble() - digits[tempInd + 1].toDouble()
                digits[tempInd] = answer.toString()
                digits.removeAt(tempInd+1)
                operators.removeAt(tempInd)
            }
        }
    }
    answer = digits[0].toDouble()
    return answer
}

fun brackets(number: String): Double{
    println("In multiplicator input num: ${number}")
    var tempStr = ""
    var number = number.replace(" ", "")
    var answer = 0.000
    var bracketOrNo = false
    while(number.toDoubleOrNull() == null){
        println("In brackets while loop")
        bracketOrNo = false
        tempStr = number
        while (tempStr.contains("(")){
            tempStr = tempStr.slice(tempStr.indexOf("(")+1..tempStr.lastIndexOf(")")-1)
            bracketOrNo = true
        }
        answer = calc(tempStr) // find value in brackets
        if(bracketOrNo){
            tempStr = "(" + tempStr + ")"
            number = number.replace(tempStr, answer.toString())
        } else {
            number = number.replace(tempStr, answer.toString())
        }
    }
    println("In brackets before return")
    return number.toDouble()
}

fun multiplicator(unit: String, importNum: String, multiplier: Double): String{ // converts the unit into its standard SA unit
    println("In multiplicator input num: ${importNum}")
    var number = importNum
    var prev = '0'
    var prevIndex = 0
    var tempNum = ""
    var tempNum2 = ""
    var newNum = ""
    var stringLength = 0
    var nums = 0
    var char2 = 'a'

    stringLength = number.length
    while(nums < stringLength){
        char2 = number[nums]
        if (char2.isDigit()){
            if (prev.isLetter() && prev != 'x'){
                number = number.replaceRange(nums, nums+1, "")
                stringLength--
                nums--
            }
        }
        nums++
        prev = char2
    }

    prevIndex = number.indexOf(unit)-1 // index of the num before cm
    prev = number[prevIndex]
    while (prev.isDigit()){ //keep going backwards
        tempNum = tempNum + prev
        if (prevIndex != 0){
            prevIndex-=1
            prev = number[prevIndex]
        } else {
            prev = 'a'
        }
    } //Temp num does reverse the number
    for (i in tempNum.length-1 downTo 0){
        tempNum2 = tempNum2 + tempNum[i]
    } //reverse the number back in tempNum2

    newNum = (tempNum2.toInt() * multiplier).toString() //convert to metre
    number = number.replace(tempNum2+unit, newNum)// replace number and cm
    return number
}

fun converter(importNum: String, importDegrees: String): String{
    println("In converter input num: ${importNum}")
    var number = importNum
    var lenDefault = ""
    var lenCount = 0
    while (isThereLetter(number)){ // If the string contains alphabet
        if (number.contains("cm")){
            number = multiplicator("cm", number, 0.01) // kill the unit
            lenDefault = "cm"
            println(number)
        } else if(number.contains("km")){ //km
            number = multiplicator("km", number, 1000.0) // kill the unit
            lenDefault = "km"
        } else if(number.contains("mm")){ //mm
            println("In converter mm ${importNum}")
            number = multiplicator("mm", number, 0.001) // kill the unit
            lenDefault = "mm"
            println("In converter mm after: ${importNum}")
        } else if(number.contains("m")){ //mm
            number = multiplicator("m", number, 1.0) // kill the unit
            lenDefault = "m"
        } else if (number.contains("in")) { //inch
            number = multiplicator("in", number, 39.3701) // kill the unit
            lenDefault = "in"
        } else if (number.contains("mi")) { //mile
            number = multiplicator("mi", number, 0.000621371) // kill the unit
            lenDefault = "mi"
        } else if (number.contains("ft")) {// in ft yd fur mi
            number = multiplicator("ft", number, 3.28084) // kill the unit
            lenDefault = "ft"
        }

    }
    return reverseUnit(brackets(number).toString(), lenDefault, importDegrees).toString() + lenDefault +importDegrees
}

fun isThereLetter(importString: String): Boolean{
    for (letter in importString){
        if (letter.isLetter() && !(letter == 'x')){
            return true
        }
    }
    return false
}

fun reverseUnit(importNum: String, importUnit: String, importDegree: String): Double{
    println(importNum)
    var number = importNum.toDouble();
    val unit = importUnit
    println(number)
    for (num in 1..importDegree.toInt()){
        if (unit == "cm"){
            number = number / 0.01
        } else if(unit == "km"){ //km
            number = number / 1000
        } else if(unit == "mm"){ //mm
            number = number / 0.001
        } else if (unit == "in") { //inch
            number = number / 0.0254
        } else if (unit == "mi") { //mile
            number = number / 0.000621371
        } else if (unit == "ft") {// in ft yd fur mi
            number = number / 3.28084
        }
    }
    return number
}

fun oneUnit(importNum: String): String{
    var aString = importNum;
    var prev = '+'
    var char2 = 'a'
    var stringLength = 0
    var nums = 0
    var units = mutableListOf<String>()
    var operators = mutableListOf<String>()
    var degrees = mutableListOf<String>()
    var tempStr = ""
    var tempInd = 0
    val lengthUnit = listOf("cm", "mm", "m", "in", "yd")
    var answer = 0

    stringLength = aString.length
    while(nums < stringLength){
        char2 = aString[nums]
        if (char2.isDigit()){
            if (prev.isDigit() || isOperation(prev)){
                aString = aString.replaceRange(nums, nums+1, "")
                stringLength--
                nums--
            }
        }
        nums++
        prev = char2
    }

    // ADD N IF NO UNIT
    nums = 0
    stringLength = aString.length // determine string length first as later we need to ++ since each n added increases string length
    while (nums < stringLength){// add n in if no unit is in the middle
        char2 = aString[nums] // assign the index to character
        if (isOperation(char2) && isOperation(prev)){ // if two operators back to back
            aString = aString.replaceRange(nums, nums+1, "n0"+char2.toString()) // replace ie + with n+
            stringLength += 2 // increase since one more char
            nums -= 1 // decrease (I got no idea why)
        }
        prev = char2 // update prev
        nums++ // next index
    }
    if (isOperation(prev)){ // if no unit is at end ie m+ with operator dangling at end
        aString  = aString + "n0" // add an n at the end
    }
    if (isOperation(aString[0])){
        aString  = "n0" + aString
    }
    // ADD THE DEGREE INTO AN ARRAY
    for (character in aString){
        if (character.isDigit()){
            degrees.add(character.toString())
        }
    }

    // REMOVE REST OF THE NUMBERS
    for (character in aString){ // remove all the numbers
        if (character.isDigit()){
            aString = aString.replace(character.toString(), "")
        }
    }

    // ORGANIZE INTO TWO ARRAYS: Units and Operators
    for (num in aString) { // create a list of all the units and operators
        if (isOperation(num)){ //units
            operators.add(num.toString()) // create a list of all the operators
            units.add(tempStr)
            tempStr = ""
        } else{
            tempStr += num
        }
    }
    units.add(tempStr)

    for (unit in 0..units.size-1){ // make everything into m
        if (units[unit] in lengthUnit){
            units.set(unit, "m")
        }
    }

    // ADD EVERYTHING TOGETHER
    while (!operators.isEmpty()){// while there are operators left in the number
        if (operators.contains("/") && operators.contains("x")){ // if thhe number has both div and mul
            if (operators.indexOf("x") > operators.indexOf("/")){// if div is before mul
                tempInd = operators.indexOf("/")
                answer = degrees[tempInd].toInt()-degrees[tempInd+1].toInt()
                degrees[tempInd] = answer.toString()
                degrees.removeAt(tempInd+1)
                operators.removeAt(tempInd)
            } else{
                tempInd = operators.indexOf("x")
                answer = degrees[tempInd].toInt()+degrees[tempInd+1].toInt()
                degrees[tempInd] = answer.toString()
                degrees.removeAt(tempInd+1)
                operators.removeAt(tempInd)
            }
        } else if (operators.contains("/")){// if multiplication theres only div
            tempInd = operators.indexOf("/")
            answer = degrees[tempInd].toInt()-degrees[tempInd+1].toInt()
            degrees[tempInd] = answer.toString()
            degrees.removeAt(tempInd+1)
            operators.removeAt(tempInd)
        } else if (operators.contains("x")) {// only mu
            tempInd = operators.indexOf("x")
            answer = degrees[tempInd].toInt()+degrees[tempInd+1].toInt()
            degrees[tempInd] = answer.toString()
            degrees.removeAt(tempInd+1)
            operators.removeAt(tempInd)
        } else {
            if (operators.indexOf("+") == -1){
                tempInd = operators.indexOf("-")
            } else {
                tempInd = operators.indexOf("+")
            }
            if (degrees[tempInd] == degrees[tempInd+1]){
                degrees.removeAt(tempInd+1)
                operators.removeAt(tempInd)
            } else {
                return "error"
            }

        }
    }
    return degrees[0];
}

fun isOperation(importNum: Char): Boolean{ // check if its an operator
    if (importNum == '+' || importNum == '-' || importNum == 'x' || importNum == '/'){
        return true
    }
    return false
}

fun entryPoint(importNum: String): String{
    var num = importNum
    var degrees = oneUnit(num)
    num = converter(num, degrees).toString()
    return num
}

@Composable
fun superscript(normalText: String, superText: String){
    Text(buildAnnotatedString{
        withStyle(
            style = SpanStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        ){
            append(normalText)
        }
        withStyle(
            style = SpanStyle(
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                fontWeight = FontWeight.Normal,
                baselineShift = BaselineShift.Superscript
            )
        ){
            append(superText)
        }
    })
}

/*
@Composable
fun InputButton(name: String, input: String, num: String, displayNum: String, onNumberChange: (String, String) -> Unit
) {
    OutlinedButton(
        onClick = {
            // Update both num (raw) and displayNum (formatted) when button is pressed
            val newNum = num + input
            val newDisplayNum = formatDisplayNumber(newNum)  // Use a helper function for formatting
            onNumberChange(newNum, newDisplayNum)
        },
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.White),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .height(70.dp)
            .width(70.dp)
    ) {
        Text(
            text = name,
            fontSize = 30.sp,
        )
    }
}
*/



@Composable
fun inputButton(name: String, input: String, number: String, onNumberChange: (String) -> Unit) {
    OutlinedButton(onClick = { onNumberChange(number + input) },
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.White),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .height(70.dp)
            .width(70.dp)
    ) {
        Text(
            text = name,
            fontSize = 30.sp,
        )
    }
}

/*@Preview(showBackground = true)
@Composable
fun InputButtonPreview() {
    var num = ""
    MyApplicationTheme {
        num = inputButton(name = "(", input = "(")
    }
}*/

@Preview(showBackground = true)
@Composable
fun InputButtonPreview() {
    var num = ""
    MyApplicationTheme {
        superscript("mm", "2")
    }
}
