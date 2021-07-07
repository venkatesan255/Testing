import com.ibm.mq.*;

MQQueueManager qMgr; // Queue Manager Class

String channel = ""; // Define Name of a Channel in string
String qManager = ""; // Define Name of a qManager in string  

qManager = "${pChannelQueueManager}"; // Assigning Value to Queue Manager by Parameterizing QManager. 

MQEnvironment mqenv = new com.ibm.mq.MQEnvironment(); // Creating an object to MQEnvironment

mqenv.hostname = "${pChannelHostName}"; // Defining HostName and Parameterizing HostName

mqenv.port = Integer.parseInt("${pChennalPortNumber}"); 

mqenv.channel = "${pChannelName}"; // Defining Channel and Parameterizing ChannelName                     

mqenv.CCSID = 1252;

mqenv.properties.put(MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES); // Set Server Connection

qMgr = new MQQueueManager(qManager); // Creating an object to MQQueueManager with qManager Name    

int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_BIND_NOT_FIXED;

queue_Meta = qMgr.accessQueue("${pQueueNameMeta}", openOptions, null, null, null);
queue_Data = qMgr.accessQueue("${pQueueNameData}", openOptions, null, null, null);

String correlationID = "AD" + ${__time(,)} + ${__iterationNum};

String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><fileActMTX xmlns=\"urn:rbs:mefg:ns.GRMeta.01\"><outboundTransmission><Filesize>100</Filesize><ApplicationID>AD</ApplicationID><InterfaceID>ADO00001</InterfaceID><Requestor></Requestor><Responder>RBOSGB2LXAD</Responder><ServiceName></ServiceName><RequestType>pain.001.001.03</RequestType><transmissionFileName>INT_12082019_statement_NFT1_Data_746.TXT</transmissionFileName><timestamp>2016-09-28T11:25:00.652Z</timestamp><business>AD</business><PossibleDuplicate>yes</PossibleDuplicate><OriginalMessageTime>2016-09-28T05:25:00.652Z</OriginalMessageTime></outboundTransmission></fileActMTX>"



MQMessage msg_meta = new MQMessage();
MQMessage msg_data = new MQMessage();

msg_meta.format = "        ";
msg_meta.feedback = 0;
msg_meta.messageType = Integer.parseInt("7000");
msg_meta.correlationId = correlationID.getBytes();
msg_meta.clearMessage();
msg_meta.messageId = MQC.MQMI_NONE;
msg_meta.writeString(msg);


msg_data.format = "        ";
msg_data.feedback = 0;
msg_data.messageType = Integer.parseInt("7001");
msg_data.correlationId = correlationID.getBytes();
msg_data.clearMessage();
msg_data.messageId = MQC.MQMI_NONE;
msg_data.writeString(msg);


MQPutMessageOptions pmo = new MQPutMessageOptions();
queue_Meta.put(msg_meta, pmo);
queue_Data.put(msg_data, pmo);

queue_Meta.close();
queue_Data.close();

qMgr.disconnect();
