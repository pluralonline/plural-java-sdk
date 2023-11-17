package com.pine_labs.pine_lab;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.google.gson.Gson;
import com.pine_lab.api.Api;
import com.pine_lab.api.EMI;
import com.pine_lab.api.Payment;

@Controller
public class Mvc_Controller implements ErrorController {

    @RequestMapping("/")
    public String home() {
    return "index";
    }

    @RequestMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("callback_url", "http://localhost:8081/orderResponse");
        return "form";
    }

    @RequestMapping("/hashVerificationView")
    public String hashVerificationView(Model model) {
        model.addAttribute("callback_url", "http://localhost:8081/orderResponse");
        return "hashView";
    }

    @RequestMapping("/hashVerificationView2")
    public RedirectView hashVerificationView2(Model model) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8081/hashVerificationView");
        return redirectView;
    }

    @RequestMapping("/orderResponse")
    public ModelAndView orderResponse(@RequestParam Map<String, Object> map) {
        ModelAndView modelAndView = new ModelAndView("orderResponse");
        modelAndView.addObject("resultData", map.toString());
        return modelAndView;
    }

    @GetMapping("/fetch")
    public ModelAndView fetchData(
            @RequestParam(name = "merchant_id", required = false) String merchant_id,
            @RequestParam(name = "access_code", required = false) String access_code,
            @RequestParam(name = "mode", required = false) String mode,
            @RequestParam(name = "secret", required = false) String secret,
            @RequestParam(name = "txn_id", required = false) String txn_id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("fetchResponse");
        try {
            Api apip = new Api(Integer.parseInt(merchant_id), access_code, secret, Boolean.parseBoolean(mode));
            Payment obj = apip.payment();
            Map<String, Object> enquiryResponse = obj.fetch(txn_id, 3);
            Gson gson = new Gson();
            String jsonResponse = enquiryResponse.get("response").toString();
            Map<String, Object> jsonMap = gson.fromJson(jsonResponse, Map.class);
            modelAndView.addObject("resultData", jsonMap);
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("resultData", "false");
            return modelAndView;
        }
    }

    @GetMapping("/emiCalculator")
    public ModelAndView emiCalculator(@RequestParam(name = "merchant_id", required = false) String merchant_id,
            @RequestParam(name = "access_code", required = false) String access_code,
            @RequestParam(name = "mode", required = false) String mode,
            @RequestParam(name = "secret", required = false) String secret,
            @RequestParam(name = "product_code1", required = false) String product_code1,
            @RequestParam(name = "product_code2", required = false) String product_code2,
            @RequestParam(name = "amount_in_paisa", required = false) String amount,
            @RequestParam(name = "product_amount1", required = false) String amount1,
            @RequestParam(name = "product_amount2", required = false) String amount2) throws Exception {
        ModelAndView modelAndView = new ModelAndView("fetchResponse");
        try {
            ArrayList<TreeMap<String, Object>> product_details = new ArrayList<TreeMap<String, Object>>();
            TreeMap<String, Object> products = new TreeMap<String, Object>();
            products.put("product_code", product_code1);
            products.put("product_amount", Long.parseLong(amount1));
            product_details.add(products);
            if(product_code2 !="" && amount2!=""){
            TreeMap<String, Object> products2 = new TreeMap<String, Object>();
            products2.put("product_code", product_code2);
            products2.put("product_amount", Long.parseLong(amount2));
            product_details.add(products2);
            }
            Api apip = new Api(Integer.parseInt(merchant_id), access_code, secret, Boolean.parseBoolean(mode));
            EMI obj = apip.emi();
            Map<String, Object> emiResponse = obj.emiCalculation(Long.parseLong(amount), product_details);
            Gson gson = new Gson();
            String jsonResponse = emiResponse.get("response").toString();
            Map<String, Object> jsonMap = gson.fromJson(jsonResponse, Map.class);
            modelAndView.addObject("resultData", jsonMap.toString());
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("resultData", "false");
            return modelAndView;
        }
    }

    @PostMapping("/hashVerification")
    public ModelAndView hashVerification(@RequestParam Map<String, Object> map) throws Exception {
        ModelAndView modelAndView = new ModelAndView("hashResponse");
        try {
            Boolean pg_mode = Boolean.parseBoolean((String) map.get("pg_mode"));
            String secret = (String) map.get("secret");
            String access_code = (String) map.get("access_code");
            Integer merchant_id = Integer.parseInt((String) map.get("merchant_id"));
            Api apip = new Api(merchant_id, access_code, secret, pg_mode);
            Payment obj = apip.payment();
            TreeMap<String, Object> dataMap = new TreeMap<>();
            dataMap.put("ppc_MerchantID", (String) map.get("merchant_id"));
            dataMap.put("ppc_MerchantAccessCode", (String) map.get("access_code"));
            dataMap.put("ppc_PinePGTxnStatus", (String) map.get("ppc_PinePGTxnStatus"));
            dataMap.put("ppc_TransactionCompletionDateTime", (String) map.get("ppc_TransactionCompletionDateTime"));
            dataMap.put("ppc_UniqueMerchantTxnID", (String) map.get("txn_id"));
            dataMap.put("ppc_Amount", (String) map.get("ppc_Amount"));
            dataMap.put("ppc_TxnResponseCode", (String) map.get("ppc_TxnResponseCode"));
            dataMap.put("ppc_TxnResponseMessage", (String) map.get("ppc_TxnResponseMessage"));
            dataMap.put("ppc_PinePGTransactionID", (String) map.get("ppc_PinePGTransactionID"));
            dataMap.put("ppc_CapturedAmount", (String) map.get("ppc_CapturedAmount"));
            dataMap.put("ppc_RefundedAmount", (String) map.get("ppc_RefundedAmount"));
            dataMap.put("ppc_AcquirerName", (String) map.get("ppc_AcquirerName"));
            dataMap.put("ppc_PaymentMode", (String) map.get("ppc_PaymentMode"));
            dataMap.put("ppc_Parent_TxnStatus", (String) map.get("ppc_Parent_TxnStatus"));
            dataMap.put("ppc_ParentTxnResponseCode", (String) map.get("ppc_ParentTxnResponseCode"));
            dataMap.put("ppc_ParentTxnResponseMessage", (String) map.get("ppc_ParentTxnResponseMessage"));
            dataMap.put("ppc_CustomerMobile", (String) map.get("ppc_CustomerMobile"));
            if (map.get("ppc_UdfField1") == null) {
                dataMap.put("ppc_UdfField1", "");
            } else {
                dataMap.put("ppc_UdfField1", map.get("ppc_UdfField1"));
            }
            if (map.get("ppc_UdfField2") == null) {
                dataMap.put("ppc_UdfField2", "");
            } else {
                dataMap.put("ppc_UdfField2", map.get("ppc_UdfField2"));
            }
            if (map.get("ppc_UdfField3") == null) {
                dataMap.put("ppc_UdfField3", "");
            } else {
                dataMap.put("ppc_UdfField3", map.get("ppc_UdfField3"));
            }
            if (map.get("ppc_UdfField4") == null) {
                dataMap.put("ppc_UdfField4", "");
            } else {
                dataMap.put("ppc_UdfField4", map.get("ppc_UdfField4"));
            }
            dataMap.put("ppc_AcquirerResponseCode", (String) map.get("ppc_AcquirerResponseCode"));
            dataMap.put("ppc_AcquirerResponseMessage", (String) map.get("ppc_AcquirerResponseMessage"));
            boolean hashVerification = obj.verifyHash((String) map.get("hash"), dataMap);
            modelAndView.addObject("status", hashVerification);
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("resultData", "false");
            return modelAndView;
        }
    }

    @PostMapping("/submit")
    public RedirectView postForm(@RequestParam Map<String, Object> map) throws Exception {
        try {
            Boolean pg_mode = Boolean.parseBoolean((String) map.get("pg_mode"));
            String secret = (String) map.get("secret");
            String access_code = (String) map.get("access_code");
            Integer merchant_id = Integer.parseInt((String) map.get("merchant_id"));
            ArrayList<String> paymentModes = new ArrayList<String>();
            TreeMap<String, Object> customerData = new TreeMap<>();
             TreeMap<String, String> billing_data = new TreeMap<>();
            if (map.get("payment_mode1") != null) {
                paymentModes.add("1");
            }
            if (map.get("payment_mode2") != null) {
                paymentModes.add("3");
            }
            if (map.get("payment_mode3") != null) {
                paymentModes.add("11");
            }
            if (map.get("payment_mode4") != null) {
                paymentModes.add("10");
            }
            if (map.get("payment_mode5") != null) {
                paymentModes.add("4");
            }
            if (map.get("payment_mode6") != null) {
                paymentModes.add("14");
            }
            if (map.get("payment_mode7") != null) {
                paymentModes.add("17");
            }
            
            String txnId = (String) map.get("txn_id");
            String callback_url = (String) map.get("callback_url");
            Long amount = Long.parseLong((String) map.get("amount_in_paisa"));
            String customerId = (String) map.get("customer_id");
            customerId.trim();
            if (customerId.length() != 0) {
                customerData.put("customer_id", customerId);
            }
            String email_id = (String) map.get("email");
            email_id.trim();
            if (email_id.length() != 0) {
                customerData.put("email_id", email_id);
            }
            String first_name = (String) map.get("first_name");
            first_name.trim();
            if (first_name.length() != 0) {
                customerData.put("first_name", first_name);
            }
            String last_name = (String) map.get("last_name");
            last_name.trim();
            if (last_name.length() != 0) {
                customerData.put("last_name", last_name);
            }
            String mobile_no = (String) map.get("phone");
            mobile_no.trim();
            if (mobile_no.length() != 0) {
                customerData.put("mobile_no", mobile_no);
            }
            String address1 = (String) map.get("address1");
            address1.trim();
            if (address1.length() != 0) {
                billing_data.put("address1", address1);
            }
            String address2 = (String) map.get("address2");
            address2.trim();
            if (address2.length() != 0) {
                billing_data.put("address2", address2);
            }
            String address3 = (String) map.get("address3");
            address3.trim();
            if (address3.length() != 0) {
                billing_data.put("address3", address3);
            }
            String city = (String) map.get("city");
            city.trim();
            if (address3.length() != 0) {
                billing_data.put("city", city);
            }
            String state = (String) map.get("state");
            state.trim();
            if (state.length() != 0) {
                billing_data.put("state", state);
            }
            String country = (String) map.get("country");
            country.trim();
            if (country.length() != 0) {
                billing_data.put("country", country);
            }
            String billing_pincode = (String) map.get("billing_pincode");
            billing_pincode.trim();
            if (billing_pincode.length() != 0) {
                billing_data.put("billing_data", billing_pincode);
            }
            TreeMap<String, String> shipping_data = new TreeMap<>();
            String shipping_firstname = (String) map.get("shipping_firstname");
            shipping_firstname.trim();
            if (shipping_firstname.length() != 0) {
                shipping_data.put("shipping_firstname", shipping_firstname);
            }
            String shipping_phone = (String) map.get("shipping_firstname");
            shipping_phone.trim();
            if (shipping_phone.length() != 0) {
                shipping_data.put("shipping_phone", shipping_phone);
            }
            String shipping_address1 = (String) map.get("shipping_address1");
            shipping_address1.trim();
            if (shipping_address1.length() != 0) {
                shipping_data.put("shipping_address1", shipping_address1);
            }
            String shipping_address2 = (String) map.get("shipping_address2");
            shipping_address2.trim();
            if (shipping_address2.length() != 0) {
                shipping_data.put("shipping_address2", shipping_address2);
            }
            String shipping_address3 = (String) map.get("shipping_address3");
            shipping_address3.trim();
            if (shipping_address3.length() != 0) {
                shipping_data.put("shipping_address3", shipping_address3);
            }
            String shipping_city = (String) map.get("shipping_city");
            shipping_city.trim();
            if (shipping_city.length() != 0) {
                shipping_data.put("shipping_city", shipping_city);
            }
            String shipping_state = (String) map.get("shipping_state");
            shipping_state.trim();
            if (shipping_state.length() != 0) {
                shipping_data.put("shipping_state", shipping_state);
            }
            String shipping_pincode = (String) map.get("shipping_pincode");
            shipping_pincode.trim();
            if (shipping_pincode.length() != 0) {
                shipping_data.put("shipping_pincode", shipping_pincode);
            }
            String shipping_country = (String) map.get("shipping_country");
            shipping_country.trim();
            if (shipping_country.length() != 0) {
                shipping_data.put("shipping_country", shipping_country);
            }
            TreeMap<String, Object> udfData = new TreeMap<>();
            String udf1 = (String) map.get("udf1");
            udf1.trim();
            if (udf1.length() != 0) {
                udfData.put("udf1", udf1);
            }
            String udf2 = (String) map.get("udf2");
            udf2.trim();
            if (udf2.length() != 0) {
                udfData.put("udf2", udf2);
            }
            String udf3 = (String) map.get("udf3");
            udf3.trim();
            if (udf3.length() != 0) {
                udfData.put("udf3", udf3);
            }
            String udf4 = (String) map.get("udf4");
            udf4.trim();
            if (udf4.length() != 0) {
                udfData.put("udf4", udf4);
            }
            customerData.put("billing_data",billing_data);
            customerData.put("shipping_data",shipping_data);
            Api apip = new Api(merchant_id, access_code, secret, pg_mode);
            Payment obj = apip.payment();
            Map<String, Object> paymentResponse = obj.create(txnId, amount, callback_url, customerData, udfData,paymentModes);
            String jsonResponse = paymentResponse.get("response").toString();
            Gson gson = new Gson();
            Map<String, Object> jsonMap = gson.fromJson(jsonResponse, Map.class);
            String token = (String) jsonMap.get("redirect_url");
            ModelAndView modelAndView = new ModelAndView("result");
            RedirectView redirectView = new RedirectView();
            if (token != null) {
                redirectView.setUrl(token);
                return redirectView;
            } else {
                modelAndView.addObject("resultData", "false");
            }
            if (token == null) {
                modelAndView.addObject("error", "Error while processing request");
            }
        } catch (Exception e) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("http://localhost:8081/form");
            return redirectView;
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8081/form");
        return redirectView;
    }

}
