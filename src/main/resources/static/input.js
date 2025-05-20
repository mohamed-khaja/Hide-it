const addBtn = document.querySelector(".add-btn");
const container = document.querySelector(".container");
const originalInput = document.querySelector(".input-block");

addBtn.addEventListener("click", () => {

    // Cloning existing .input  elements
    const clonedInput = originalInput.cloneNode(true);

    // removing the text/secret entered in original .input
    const inputs = clonedInput.querySelectorAll(`input[type="text"]`)
    inputs.forEach(input => input.value = '');

    const resultBox = clonedInput.querySelector(`.result-box`)
    resultBox.innerText = ''

    const removeBtn = document.createElement("button")
    removeBtn.textContent = 'Remove';
    removeBtn.className = 'remove-btn';
    removeBtn.addEventListener("click", () => {
        container.removeChild(clonedInput)
    })

    clonedInput.appendChild(removeBtn)
    const submit = document.querySelector('.submit-btn')
    container.insertBefore(clonedInput, submit)
})