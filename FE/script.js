//http://localhost:8080/api/employee
const HOST = "http://localhost:8080";

document
  .getElementById("employeeForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();
    const name = document.getElementById("name").value;
    const cityName = document.getElementById("city").value;
    const postalCode = document.getElementById("postalCode").value;

    //fetch, axios
    fetch(`${HOST}/api/employee`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: name,
        address: {
          city: cityName,
          postalCode: postalCode,
        },
      }),
    })
      .then((response) => response.json())
      .then(
        (data) =>
          (document.getElementById("result").innerText =
            "the newly created emp -> " + data)
      )
      .catch((error) => (document.getElementById("result").innerText = error));
  });
