import { Component, OnInit } from '@angular/core';
import { mixinColor } from '@angular/material/core';
import { CalculatorService } from '../rest/calculator.service';
import { Result } from '../models/result.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

  //its a flag that identify if the user have inserted the first value, if so we need to remove the zero from the operator1
  firstValue : boolean;

  //only one point is permitted
  pointInsertedOperator1 : boolean = false;
  pointInsertedOperator2 : boolean = false;

  //If the operation is selected or not!
  //if so disable other operation buttons
  operationSelected : boolean = false;

  //The value that is displayed 
  displayedValue : string = '0';

  //The duration of the SnackBar (Ref: https://material.angular.io/components/snack-bar/examples)
  // 5 seconds
  durationInSeconds = 5*1000;

  //error message 
  errorMessage: string;

  //fibonacciButtonVisible
  fibonacciButtonVisible : boolean = true;

  operator1 : string = '0';
  operator2 : string = '';
  operation : string;

  //Injecting the services into the component
  constructor(private restCalculator : CalculatorService, private snackBar: MatSnackBar) { 
    this.firstValue = true;
  }

  ngOnInit() {
  }

  //function that bind the click from numberic button in html
  click(numberInput: string){
    this.displayedValue = this.displayedValue.concat(numberInput);
    if(this.operation == undefined){
      if(this.firstValue){
        console.log("firstValue = " + this.firstValue);
        this.operator1 = numberInput.toString();
        this.displayedValue = numberInput.toString();
        this.firstValue = false;
      }else{        
        console.log("firstValue = " + this.firstValue + " numberInput " + numberInput);
        this.operator1 = this.operator1.concat(numberInput);
      }
    }else{
      this.operator2 = this.operator2.concat(numberInput);
    }
  }

 setOperation(operation: string, symbol: string){
    this.operation = operation;
    this.displayedValue = this.displayedValue.concat(symbol);
    this.operationSelected = true;
    this.fibonacciButtonVisible = false;
  }

  //reset all operation
  delete(){
    this.operator1 = '0';
    this.operator2 = '';
    this.firstValue = true;
    this.operation = undefined;
    this.errorMessage = '';
    this.displayedValue = '0';
    this.pointInsertedOperator1 = false;
    this.pointInsertedOperator2 = false;
    this.operationSelected = false;
    this.fibonacciButtonVisible = true;
  }

  //insert point
  insertPoint(){
    if(this.operation == undefined){
      this.pointInsertedOperator1 = true;
      this.displayedValue = this.displayedValue.concat('.');
      this.operator1 = this.operator1.concat('.');
    }else{
      this.pointInsertedOperator2 = true;
      this.displayedValue = this.displayedValue.concat('.');
      this.operator2 = this.operator2.concat('.');
    }
    this.fibonacciButtonVisible = false;
  }

  result(){
    console.log(Number.parseFloat(this.operator1) + " " + Number.parseFloat(this.operator2));
    //calling che RESTful Service
    this.restCalculator.doOperation(Number.parseFloat(this.operator1), Number.parseFloat(this.operator2), this.operation).subscribe(
      res =>{
        if(!res.error){
          this.displayedValue = res.result.toString();
          this.operator1 = res.result.toString();
          this.operationSelected = false;
          this.operator2 = '';
          this.operation = undefined;
        }else{
          this.errorMessage = res.message;
          this.openSnackBar(this.errorMessage, null, this.durationInSeconds);
        }
      },
      error => {
        console.log(error);
       this.handleErrors(error);
      });
  }

  pointDisable(){
    if(this.operation == undefined){
      return this.pointInsertedOperator1;
    }else{
      return this.pointInsertedOperator2;
    }
  }

  resultButtonVisible(){
    return this.operator1.length > 0 && this.operator2.length > 0 && this.operation.length > 0; 
  }

  openSnackBar(message: string, action: string, duration: number) {
    console.log("OpenSnackBar --> " + message);
    this.snackBar.open(message, action, {
      duration: duration,
    });
  }

  private handleErrors(error: HttpErrorResponse) {
    this.errorMessage = "Error: can't connect the RESTful web service. " + error.message;
    this.openSnackBar(this.errorMessage, null, this.durationInSeconds);
  }

  fibonacciSeries(){
    if(this.operator1.length > 0){
      this.restCalculator.fibonacci(this.operator1).subscribe(
        res =>{
          if(!res.error){
            this.displayedValue = res.message;
            this.operationSelected = false;
            this.operator2 = '';
            this.operator1 = '0'
            this.operation = undefined;
          }else{
            this.errorMessage = res.message;
            this.openSnackBar(this.errorMessage, null, this.durationInSeconds);
          }
          this.fibonacciButtonVisible = false;
        },
        error => {
          console.log(error);
         this.handleErrors(error);
        });
    }
  }

}
