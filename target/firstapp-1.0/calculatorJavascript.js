var currentCalc = 0;
var calc = null;
var calcuation= null;
var math_it_up = {
    '+': function (x, y) {
        return x + y
    },
    '/': function (x, y) {
        return x / y
    },
    '*': function (x, y) {
        return x * y
    },
    '-': function (x, y) {
        return x - y
    },
    '=': function (x, y) {
        return 0
    }
};
var buttonList  = document.getElementsByTagName("button");
for (i = 0; i < buttonList.length; i++){
    buttonList[i].onclick = function(){
        var display = document.getElementById("disp");
        var calculation = document.getElementById("calculation");
        if(isNaN(this.innerText)){
            if(this.innerText=="C"){
                display.innerText = "0";
            }
            else{
            if(calc==null&&this.innerText!="="){
                calculation.innerText =  display.innerText+this.innerText;
                calc=this.innerText;
                currentCalc = parseFloat(display.innerText);
                display.innerText = "0";
            }
            else{
                if(this.innerText=="=" && currentCalc!=null && calc!=null){
                    calculation.innerText +=  display.innerText;
                    display.innerText = math_it_up[calc](currentCalc,parseFloat(display.innerText));
                    currentCalc = null;
                    calc=null;
                }
                else{
                    if(currentCalc==null || calc==null){
                        display.innerText = "0";
                        currentCalc = 0;
                        calc=null;
                    }
                    else{
                        calculation.innerText +=  display.innerText+this.innerText;
                        currentCalc = math_it_up[calc](currentCalc,parseFloat(display.innerText));
                        calc=this.innerText;
                        display.innerText = "0";
                    }
                }
            }

        }
        }
        else{
        if(display.innerText!=0) {
            if(currentCalc==null){
                display.innerText = this.innerText;
                calculation.innerText =  "-";
                currentCalc = 0;
            }
            else{
        display.innerText += this.innerText;
            }
        }
        else{
            display.innerText = this.innerText;
        }
        }
    }
}

