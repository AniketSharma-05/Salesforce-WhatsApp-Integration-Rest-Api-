# Salesforce-WhatsApp Integration (REST API)

This project integrates Salesforce with WhatsApp via the REST API, allowing custom message templates to be sent to contacts. It includes an Apex controller for handling the API callouts and a Lightning Web Component (LWC) for user interaction. Here’s an overview of the components, their functionality, and how they work together.

## **Business Logic**

This integration enables you to:
1. Connect Salesforce with WhatsApp using REST API.
2. Call Apex methods from Lightning Web Components (LWC) using the imperative approach.
3. Create and send custom WhatsApp message templates from Salesforce.
4. Implement a REST API callout from Apex to WhatsApp.
5. Enable message sending to Salesforce contacts on a button click.

## **Components**

### **SendWhatsAppMessage.html**
This component provides a user interface in the form of a card with a "Send" button. When clicked, the button triggers the `onSendTextTemplate` function in the JavaScript controller.

### **SendWhatsAppMessage.js**
This JavaScript file controls the logic for handling the button click in the HTML component. It uses the imperative method to call an Apex method and display feedback to the user through toast messages:
- **onSendTextTemplate()**: This function calls the `sendTemplateMessage` method in Apex, passing the current `recordId` (Contact's ID) as a parameter.
- **Toast Messages**: Based on the response from the Apex method, the function displays a toast notification to indicate success or failure of the message delivery.

### **WhatsAppIntegrationController.apxc**
This Apex controller handles the API callout to WhatsApp and the logic to format and send a message template:
- **sendTemplateMessage**: This method takes a `contactId` parameter to retrieve the contact’s phone number from Salesforce. 
- **API Callout**: The method constructs an HTTP request, setting the endpoint, headers, and body to match the WhatsApp API requirements.
    - **Token Authorization**: An API token is set in the authorization header to authenticate the call.
    - **Request Body**: The JSON body includes the message template to be sent to WhatsApp.
- **Response Handling**: The method returns `true` if the message was successfully delivered (status code 200 or 201), and `false` if there was an error.
