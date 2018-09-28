# NYNewsApp
-- Brief on the Implementation --
The NYNewsApp is the app designed to consume the following API:
http://api.nytimes.com/svc/mostpopular/v2/mostviewed/{section}/{period}.json?apikey=sample-key	

HomeActivity is the splash screen which remains for 4 seconds
MainActivity consists of Navigation View, TabtoolBar to load the fragments(Most Viewed,Sports, Top Stories etc)
Server data fetch is used only for the Most Viewed fragments. Data fetch is done using the async task and the response JSON data is 
converted to POJO using the GSON library and the news articles are loaded using the Recycler View, each article is placed in the card view 
consisting of title, publisher and few info.
On click of card view the webview is opened which loads the article using the URL fetched by the abv api.

Test is conducted for HomeActivity and MainActiving using unit test and espresso to check if the activites and fragments are being launched 
or not.

build and run the source code in the android studio and launch the app. use the navigation view or the tabview to navigate to different sections
run the test cases from the AndroidTest package.
