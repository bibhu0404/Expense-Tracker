const BASE_URL = "http://localhost:9090/api/expenses";


// Load all expenses initially
async function loadExpenses() {

    try {

        const response = await fetch(BASE_URL);

        const expenses = await response.json();

        displayExpenses(expenses);

        calculateSummary(expenses);

    } 
    catch(error) {

        console.log("Error loading expenses:", error);

    }

}



// Display expenses in table
function displayExpenses(expenses) {


    const tableBody =
        document.getElementById("expenseTable");


    let table = "";


    expenses.forEach(expense => {


        table += `

        <tr>

            <td>${expense.title}</td>

            <td>₹${expense.amount}</td>

            <td>${expense.category}</td>

            <td>${expense.expenseDate}</td>

            <td>

                <button onclick="editExpense(${expense.id})">
                    Edit
                </button>


                <button onclick="deleteExpense(${expense.id})">
                    Delete
                </button>

            </td>

        </tr>

        `;


    });


    tableBody.innerHTML = table;


}



// Calculate dashboard summary
function calculateSummary(expenses) {


    let total = 0;


    expenses.forEach(expense => {

        total += expense.amount;

    });


    document.getElementById("totalExpense")
        .innerText = "₹" + total;


    document.getElementById("totalTransactions")
        .innerText = expenses.length;


}



// Delete expense
async function deleteExpense(id) {


    const response = await fetch(
        BASE_URL + "/" + id,
        {
            method: "DELETE"
        }
    );


    if(response.ok){

        alert("Expense deleted");

        loadExpenses();

    }
    else{

        alert("Delete failed");

    }

}



// Redirect to edit page
function editExpense(id) {


    window.location.href =
        "edit-expense.html?id=" + id;

}



// Search expense by title
async function searchExpense() {


    const keyword =
        document.getElementById("searchInput").value;


    if(keyword.trim() === ""){

        loadExpenses();

        return;

    }


    const response = await fetch(
        BASE_URL + "/search?keyword=" + keyword
    );


    const expenses =
        await response.json();


    displayExpenses(expenses);

    calculateSummary(expenses);


}



// Filter by category
async function filterCategory() {


    const category =
        document.getElementById("categoryFilter").value;



    if(category === ""){

        loadExpenses();

        return;

    }



    const response = await fetch(
        BASE_URL + "/category/" + category
    );


    const expenses =
        await response.json();



    displayExpenses(expenses);

    calculateSummary(expenses);


}



// Run when dashboard opens
loadExpenses();