const submitBtn = document.querySelector(".submit-btn")

submitBtn.addEventListener("click", async (e) => {
    e.preventDefault()  // to prevent default behaviour ; by default once submit button is clicked - page refersh

    const radioBtn = document.querySelector(`input[name="action"]:checked`)  // get the radio buttons value
    const action = radioBtn.value

    const inputBlock = document.querySelectorAll(`.input-block`)

    const requestBody = []

    inputBlock.forEach((block) => {
        const inputs = block.querySelectorAll(`input[type="text"]`)  // this will store the text & secret of one input block as array / nodeblock
        const text = inputs[0].value.trim()
        const secret = inputs[1].value.trim()

        if (text && secret) {
            requestBody.push({ text, secret })
        }
    })

    if (requestBody.length === 0) {
        alert("Enter atleast one inputs")
    }

    const url = `http://localhost:9999/${action}`

    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(requestBody)
        })

        if (!response.ok) {
            throw new Error("Unexected Network Error" + response.status)
        }
        const result = await response.json()

        console.log(result);

        result.forEach((res, index) => {
            console.log(res)
            const block = inputBlock[index]
            const resultBox = block.querySelector('.result-box')
            resultBox.value = res || "No Results"
        })
    } catch (error) {
        console.error(error)
    }
})