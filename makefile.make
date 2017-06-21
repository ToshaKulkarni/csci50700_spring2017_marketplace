JC = javac -cp ".:./mysql-connector-java-5.1.41-bin.jar"
JA = java
.SUFFIXES: .java .class
.java.class:
	$(JC) -g $*.java

CLASSES = \
Activity.java\
ActivityControl.java\
AdminView.java\
AuthorizationException.java\
AuthorizationInvocationHandler.java\
BrowseItems.java\
BuyItems.java\
Cart.java\
Client.java\
CustomerView.java\
Dispatcher.java\
FrontController.java\
Items.java\
LoginController.java\
Market.java\
MarketClient.java\
MktCustomer.java\
RegistrationController.java\
RequiresRole.java\
Server.java\
ServerImpl.java\
ServerInterface.java\
Session.java\
UpdateItems.java\
User.java\


default:  clean classes

classes: $(CLASSES:.java=.class)

run:
	$(JA) TestThreadcomm

clean:
	$(RM) *.class
