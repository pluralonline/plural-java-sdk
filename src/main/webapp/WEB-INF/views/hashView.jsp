<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Pinelabs Java/Springboot TEST</title>
    
        <style>
            .dropdown-menu { padding: 15px;  max-height: 200px; overflow-x: hidden; } 
            .dropdown-menu a {  display: block; } 
            .dropdown-menu a:hover { background-color: #f1f1f1; }
            /* .col-md-12.text-center a + a::before {
              content: "|";
              padding: 0 10px;
            } */
        </style>
    </head>
<body>
    <body>
        <div class="container">
            <main>
                <div class="py-5 text-center">
                    <h2>Test Form</h2>
                    <div class="text-center"> 
                        <p class="mb-0">Hash Verification</p>
                        <!-- <div class="col-md-12 text-center">
                            <a href="http://localhost:8081/fetch" id="fetchLink" target="_blank">Fetch Order Details</a>
                            <span>|</span>
                            <a href="http://localhost:8081/emi" id="emiLink" target="_blank">Order EMI Details</a>
                            <span>|</span>
                            <a href="http://localhost:8081/hash" id="emiLink" target="_blank">Hash Verification</a>
                        </div>   -->
                    </div>
                </div> 
    
                <div class="row">
                    <div class="col-md-6 col-lg-12">
                        <form method="post" action="hashVerification"class="needs-validation">
                            <div class="row g-3">
                                <div class="col-sm-6">
                                    <label for="mid" class="form-label">Merchant ID</label>
                                    <input type="text" name="merchant_id" class="form-control" id="mid" placeholder="Merchant ID" value="106600" required>
                                </div>
    
                                <div class="col-sm-6">
                                    <label for="access_code" class="form-label">Access Code</label>
                                    <input type="text" name="access_code" class="form-control" id="access_code" placeholder="API Access Code" value="bcf441be-411b-46a1-aa88-c6e852a7d68c" required>
                                </div>
                                <div class="col-sm-6">
                                    <label for="access_code" class="form-label">Hash</label>
                                    <input type="text" name="hash" class="form-control" id="access_code" placeholder="Enter Hash you want to verify" value="D640CFF0FCB8D42B74B1AFD19D97A375DAF174CCBE9555E40CC6236964928896" required>
                                </div>
                                <div class="col-sm-6">
                                    <label for="secret" class="form-label">Secret</label>
                                    <input type="text" name="secret" class="form-control" id="secret" placeholder="Secret" value="9A7282D0556544C59AFE8EC92F5C85F6" required>
                                </div>
    
                                <div class="col-sm-6">
                                    <label for="mode" class="form-label">Gateway Mode</label>
                                    <select name="pg_mode" id="mode" class="form-control">
                                        <option value="true">Sandbox</option>
                                        <option value="false">Production</option>
                                    </select>
                                </div>
    
                                <div class="col-sm-3">
                                    <label for="txn_id" class="form-label">Transacrtion ID</label>
                                    <input type="text" name="txn_id" class="form-control" id="txn_id" placeholder="Transacrtion ID" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="txn_id" class="form-label">Pine Pg Transaction ID</label>
                                    <input type="text" name="ppc_PinePGTransactionID" class="form-control" id="txn_id" placeholder="Enter Pine Pg Txn Id" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_PinePGTxnStatus" class="form-label">Transaction Status</label>
                                    <input type="text" name="ppc_PinePGTxnStatus" class="form-control" id="txn_id" placeholder="Pine Pg txn Status" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_TransactionCompletionDateTime" class="form-label">Transaction date</label>
                                    <input type="text" name="ppc_TransactionCompletionDateTime" class="form-control" id="txn_id" placeholder="Transaction Completion date" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_Amount" class="form-label">Enter PPC amount</label>
                                    <input type="text" name="ppc_Amount" class="form-control" id="txn_id" placeholder="Enter PPC amount" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_TxnResponseCode" class="form-label">Transaction Response Code</label>
                                    <input type="text" name="ppc_TxnResponseCode" class="form-control" id="txn_id" placeholder="Enter Txn Response Code" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_TxnResponseMessage" class="form-label">Transaction Response Message</label>
                                    <input type="text" name="ppc_TxnResponseMessage" class="form-control" id="txn_id" placeholder="Enter Txn Response Message" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_CapturedAmount" class="form-label">Captured Amount</label>
                                    <input type="text" name="ppc_CapturedAmount" class="form-control" id="txn_id" placeholder="Enter captured Amount" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_RefundedAmount" class="form-label">Refunded amount</label>
                                    <input type="text" name="ppc_RefundedAmount" class="form-control" id="txn_id" placeholder="Enter Refunded amount" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_AcquirerName" class="form-label">Acquirer name</label>
                                    <input type="text" name="ppc_AcquirerName" class="form-control" id="txn_id" placeholder="Enter acquirer name" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_PaymentMode" class="form-label">Payment Mode</label>
                                    <input type="text" name="ppc_PaymentMode" class="form-control" id="txn_id" placeholder="Enter Payment mode" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_Parent_TxnStatus" class="form-label">Parent Transaction Status</label>
                                    <input type="text" name="ppc_Parent_TxnStatus" class="form-control" id="txn_id" placeholder="Enter parent txn status" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="txn_id" class="form-label">Parent Transaction Response code</label>
                                    <input type="text" name="ppc_ParentTxnResponseCode" class="form-control" id="txn_id" placeholder="Enter Parent txn response code" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_ParentTxnResponseMessage" class="form-label">Parent Transaction Response Message</label>
                                    <input type="text" name="ppc_ParentTxnResponseMessage" class="form-control" id="txn_id" placeholder="Enter parent txn response message" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_CustomerMobile" class="form-label">Customer Mobile No</label>
                                    <input type="text" name="ppc_CustomerMobile" class="form-control" id="txn_id" placeholder= "Enter customer mobile no" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_AcquirerResponseCode" class="form-label">Acquirer Response Code</label>
                                    <input type="text" name="ppc_AcquirerResponseCode" class="form-control" id="txn_id" placeholder= "Enter Acquirer Response Code" required>
                                </div>
                                <div class="col-sm-3">
                                    <label for="ppc_AcquirerResponseMessage" class="form-label">Acquirer Response Message</label>
                                    <input type="text" name="ppc_AcquirerResponseMessage" class="form-control" id="txn_id" placeholder= "Enter Acquirer Response Message" required>
                                </div>
                                <a class="text-dark text-decoration-none mt-2 mb-4 d-flex" href="#additional_fields" data-bs-toggle="collapse" role="button">
                                    <i class="fa fa-chevron-down me-2 text-primary"></i>
                                    <h5 class="mb-0">Additional Fields </h5>
                                </a>
    
                                <div id="additional_fields" class="collapse row">
    
                                    <div class="mb-3 col-md-6">
                                        <label for="udf1">udf 1</label>
                                        <input type="text" class="form-control" id="udf1" placeholder="Enter udf 1" name="ppc_UdfField1">
                                    </div>
    
                                    <div class="mb-3 col-md-6">
                                        <label for="udf2">udf 2</label>
                                        <input type="text" class="form-control" id="udf2" placeholder="Enter udf 2" name="ppc_UdfField2">
                                    </div>
    
                                    <div class="mb-3 col-md-6">
                                        <label for="udf3">udf 3</label>
                                        <input type="text" class="form-control" id="udf3" placeholder="Enter udf 3" name="ppc_UdfField3">
                                    </div>
    
                                    <div class="mb-3 col-md-6">
                                        <label for="udf4">udf 4</label>
                                        <input type="text" class="form-control" id="udf4" placeholder="Enter udf 4" name="ppc_UdfField4">
                                    </div>
    
                                </div>
                            </div>
    
                            <button class="w-100 my-4 btn btn-primary btn-lg" type="submit" name="pay_now">Verify Hash</button>
                        </form>
                    </div>
                </div>
            </main>
    
        </div>
    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        
    </body>
</body>
</html>
