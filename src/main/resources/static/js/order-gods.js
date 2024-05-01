function addToCart(id) {
    let productImg = document.getElementById("img" + id);
    let imgSrc = productImg.getAttribute("src");
    let imgSrcString = String(imgSrc);
    let productName = document.getElementById("title" + id).innerText;
    let productPrice = document.getElementById("info" + id).innerText;
    productPrice = parseInt(productPrice.replaceAll(' ', '').slice(0, -4));
    let inputStepper = document.getElementById("quantity" + id);
    let productAmount = parseInt(inputStepper.value);
    let item = {
        id: id,
        img: imgSrcString,
        name: productName,
        price: productPrice,
        amount: productAmount,
    };
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let existingItem = cart.find(cartItem => cartItem.name === item.name);
    if (existingItem) {
        existingItem.amount += item.amount;
    } else {
        cart.push(item);
    }
    localStorage.setItem('cart', JSON.stringify(cart));
}

function decrementQuantity(id) {
    let quantityInput = document.getElementById("quantity" + id);
    if (parseInt(quantityInput.value) > 1) {
        quantityInput.value = parseInt(quantityInput.value) - 1;
    }
}

function incrementQuantity(id) {
    let quantityInput = document.getElementById("quantity" + id);
    quantityInput.value = parseInt(quantityInput.value) + 1;
}

function fillCartItems() {
    let cartItemsElement = document.getElementById("cart-items");
    cartItemsElement.innerHTML = "";
    let summ = 0;
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    if (cart.length == 0) {
        window.location.href = 'catalog';
    }
    for (let ind = 0; ind < cart.length; ind++) {
        let cartItemElement = document.createElement('div');
        cartItemElement.classList.add('oneGood');
        cartItemElement.innerHTML = `
            <div class="card">
                <img src="${cart[ind].img}" alt="${cart[ind].name}">
                <p>${cart[ind].name}</p>
            </div>
            <p>${cart[ind].amount}</p>
            <p>${cart[ind].price} ₽</p>
            <div class = "removeAndPrice">
                <p>${cart[ind].amount * cart[ind].price} ₽</p>
                <button class="remove-btn" onclick="removeItem(${ind})"><i class="fa fa-trash" aria-hidden="true"></i></button>
            </div>`;
        let cartIndex = document.getElementById('index' + ind);
        cartIndex.value = parseInt(cart[ind].id);
        let cartName = document.getElementById('name' + ind);
        cartName.value = cart[ind].name;
        let cartAmount = document.getElementById('amount' + ind);
        cartAmount.value = parseInt(cart[ind].amount);
        let cartFlowerPrice = document.getElementById('flowerPrice' + ind);
        cartFlowerPrice.value = parseInt(cart[ind].price);
        summ += cart[ind].amount * cart[ind].price;
        cartItemsElement.appendChild(cartItemElement);
    }
    let quantitySumm = document.getElementById(`text2`);
    quantitySumm.value = parseInt(quantitySumm.value | 0) + summ;
}

function removeItem(index) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart.splice(index, 1);
    localStorage.setItem('cart', JSON.stringify(cart));
    submitForm();
    fillCartItems();
}

function removeAll() {
    localStorage.removeItem('cart');
    window.location.href = 'account';
}
