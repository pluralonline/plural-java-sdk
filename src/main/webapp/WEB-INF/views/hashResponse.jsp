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
            // var generatedHash = "${generatedHash}";
            // var receivedHash = "${receivedHash}";
            var status="${status}";

            var p1 = document.createElement("p");
            p1.textContent = "Verification Status: ";
            var span1 = document.createElement("span");
            if(status=="true"){
                status="True";
            }
            else{
                status="False";
            }
            span1.textContent = status // Replace with the actual error code value
            span1.style.fontWeight = "bold";
            p1.appendChild(span1);
            document.body.appendChild(p1);
        }

        // Call the showMessage function when the page loads
        window.onload = showMessage;
    </script>
</body>

</html>