// Users
let customers = [
    { username: "mageshwaran", password: "1234" },
    { username: "parasuraman", password: "1234" },
    { username: "dhilipkumar", password: "1234" },
    { username: "manikandan", password: "1234" }
];

let librarians = [...customers];

// Books
let books = [
    { title: "Java Basics", isIssued: false },
    { title: "Object Oriented Design", isIssued: false },
    { title: "Data Structures", isIssued: false }
];

let currentRole = "";
let currentUser = "";

function login() {
    let role = document.getElementById("role").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let users = role === "customer" ? customers : librarians;

    let user = users.find(u => u.username === username && u.password === password);

    if (user) {
        currentRole = role;
        currentUser = username;
        document.getElementById("loginSection").classList.add("hidden");
        document.getElementById("menuSection").classList.remove("hidden");
        document.getElementById("welcome").innerText = `Welcome ${username} (${role})`;
        showMenu();
    } else {
        document.getElementById("loginMsg").innerText = "Login failed!";
    }
}

function logout() {
    document.getElementById("menuSection").classList.add("hidden");
    document.getElementById("bookSection").classList.add("hidden");
    document.getElementById("loginSection").classList.remove("hidden");
}

function showMenu() {
    let menu = document.getElementById("menuButtons");
    menu.innerHTML = "";

    if (currentRole === "customer") {
        menu.innerHTML += `<button onclick="viewBooks()">View Books</button>`;
        menu.innerHTML += `<button onclick="requestBook()">Request Book</button>`;
        menu.innerHTML += `<button onclick="collectBook()">Collect Book</button>`;
    } else {
        menu.innerHTML += `<button onclick="orderBook()">Order Book</button>`;
        menu.innerHTML += `<button onclick="issueBook()">Issue Book</button>`;
        menu.innerHTML += `<button onclick="viewBooks()">View Books</button>`;
    }
}

function viewBooks() {
    document.getElementById("bookSection").classList.remove("hidden");
    let list = document.getElementById("bookList");
    list.innerHTML = "";

    books.forEach((book, index) => {
        list.innerHTML += `<li>${index + 1}. ${book.title} 
        (${book.isIssued ? "Issued" : "Available"})</li>`;
    });
}

function requestBook() {
    viewBooks();
    alert("Select a book number from list and request manually (UI simplified).");
}

function collectBook() {
    let title = prompt("Enter book title to collect:");
    let book = books.find(b => b.title.toLowerCase() === title.toLowerCase() && !b.isIssued);

    if (book) {
        book.isIssued = true;
        alert("Book collected successfully!");
        viewBooks();
    } else {
        alert("Book not available.");
    }
}

function orderBook() {
    let title = prompt("Enter new book title:");
    if (title) {
        books.push({ title: title, isIssued: false });
        alert("New book added!");
        viewBooks();
    }
}

function issueBook() {
    viewBooks();
    let index = prompt("Enter book number to issue:");
    let idx = index - 1;

    if (books[idx] && !books[idx].isIssued) {
        books[idx].isIssued = true;
        alert("Book issued!");
        viewBooks();
    } else {
        alert("Invalid selection or already issued.");
    }
}
