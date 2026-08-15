const BASE_URL =
"http://localhost:9090/api/expenses";



document
.getElementById("expenseForm")
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



const response=await fetch(BASE_URL,{

method:"POST",

headers:{

"Content-Type":"application/json"

},

body:JSON.stringify(expense)

});



if(response.ok){

alert("Expense Added");

window.location.href="index.html";


}

else{

alert("Error adding expense");

}



});