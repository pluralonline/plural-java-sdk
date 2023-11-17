<!DOCTYPE html>
<html>
<head>
    <title>Fetch Order Response</title>

</head>
<body>
    <!-- <h1>Please Wait!</h1> -->
    <script type="text/javascript">
        // Your JavaScript code goes here
        function showMessage() {
            var resultData = "${resultData}";

            if (resultData !== "false") {
                var pre = document.createElement("pre");

            // Set CSS style to limit width and enable horizontal scrolling
            pre.style.whiteSpace = "pre-wrap";
            pre.style.overflowX = "auto";
            pre.style.maxWidth = "100%";
            
            // Set the JSON data as text content
            pre.textContent = JSON.stringify(resultData, null, 2);
            
            // Append the <pre> element to the document body
            document.body.appendChild(pre);
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
