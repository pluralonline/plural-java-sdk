<!DOCTYPE html>
<html>
<head>
    <title>Create Order Redirect</title>
</head>
<body>
    <!-- <h1>Please Wait!</h1> -->
    <script type="text/javascript">
        // Your JavaScript code goes here
        function showMessage() {
            var resultData = "${resultData}";

            if (resultData !== "false") {
                // If resultData is "true," show the anchor tag
                var anchor = document.createElement("a");
                anchor.href = resultData;
                anchor.textContent = "Redirect to Payment Page";
                document.body.appendChild(anchor);
            }
            else{
                var h1 = document.createElement("h1");
                h1.textContent = "Error while processing request";
                document.body.appendChild(h1);
            }
        }

        // Call the showMessage function when the page loads
        window.onload = showMessage;
    </script>
</body>

</html>