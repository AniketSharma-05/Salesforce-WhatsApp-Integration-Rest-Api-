public class WhatsAppIntegrationController {
    
    //This method sends message template to the contact on WhatsApp
    @AuraEnabled
    public static Boolean sendTemplateMessage(String contactId) {
        
        try {
            
            //Get contact details for phone number
            Contact contactDetail = [ Select Id,Phone From Contact Where Id =: contactId];
            
            String whatsAppNumber = '91' + contactDetail.Phone;
            system.debug('whatsAppNumber:'+whatsAppNumber);
            
            Http http = new Http();
            
            HttpRequest req = new HttpRequest();
            
            //Rest Api -> https://graph.facebook.com/v13.0/Phone number ID/messages 
            //(Phone number ID -> is available in your app create on meta developer console)
            
            req.setEndpoint('https://graph.facebook.com/v13.0/101349485929200/messages');
            req.setHeader('Content-Type', 'application/json');
            req.setMethod('POST');
            
            String body = '{ "messaging_product": "whatsapp", "to": "'+whatsAppNumber +'", "type": "template", "template": { "name": "hello_world", "language": { "code": "en_US" } } }';
            req.setBody(body);
            
            //Get token from the app you created in meta developer console
            String apiToken='YOUR_TOKEN';
            req.setHeader('Authorization', 'Bearer '+apiToken);
            
            HttpResponse res = http.send(req);
            
            System.debug(''+res.getStatusCode());
            System.debug(''+res.getBody());
            
            if(res.getStatusCode() == 200 || res.getStatusCode() == 201){
                return true;
            }
            return false;   
            
        } catch (Exception e) {
            System.debug('e.getMessage():'+e.getMessage());
            return false;
            
        } 
        
    }
}