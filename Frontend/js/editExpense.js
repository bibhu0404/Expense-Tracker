const BASE_URL =
"http://localhost:9090/api/expenses";


const params =
new URLSearchParams(window.location.search);


const id = params.get("id");



async function loadExpense(){


const response =
await fetch(BASE_URL+"/"+id);


const expense =
await response.json();



document.getElementById("title").value =
expense.title;


document.getElementById("amount").value =
expense.amount;


document.getElementById("category").value =
expense.category;


document.getElementById("expenseDate").value =
expense.expenseDate;


document.getElementById("description").value =
expense.description;


}


loadExpense();




document
.getElementById("editForm")
.addEventListener("submit",async e=>{


e.preventDefault();



const expense={


title:
document.getElementById("title").value,


amount:
Number(document.getElementById("amount").value),


category:
document.getElementById("category").value,


expenseDate:
document.getElementById("expenseDate").value,


description:
document.getElementById("description").value


};



const response =
await fetch(BASE_URL+"/"+id,{

method:"PUT",

headers:{

"Content-Type":"application/json"

},

body:
JSON.stringify(expense)

});



if(response.ok){

alert("Expense Updated");

window.location.href="index.html";

}


});