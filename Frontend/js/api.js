const BASE_URL = "http://localhost:9090/api/expenses";


async function getExpenses(){

    const response = await fetch(BASE_URL);

    const expenses = await response.json();


    displayExpenses(expenses);

    calculateSummary(expenses);
}



function displayExpenses(expenses){

    const tableBody =
        document.getElementById("expenseTableBody");


    tableBody.innerHTML = "";


    expenses.forEach(expense => {


        const row = document.createElement("tr");


        row.innerHTML = `

            <td>${expense.title}</td>

            <td>₹${expense.amount}</td>

            <td>${expense.category}</td>

            <td>${expense.expenseDate}</td>

        `;


        tableBody.appendChild(row);

    });

}



function calculateSummary(expenses){


    let total = 0;


    expenses.forEach(expense => {

        total += expense.amount;

    });


    document.getElementById("totalExpense")
        .innerText = "₹" + total;


    document.getElementById("totalTransactions")
        .innerText = expenses.length;

}



getExpenses();