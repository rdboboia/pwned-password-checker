# pwned-password-checker


### Intro
A quick implementation of a Java app that checks if a given password was leaked in the "haveibeenpwned" database. I uploaded an already compiled .jar if you just want to download and use the app.

### About my implementation (disclaimer)
Since my app is dependant of an external API, I didn't followed some well-known best practices on purpose. My goal was to make the implementation as fast and simple as possible. Another reason is that I wanted the entire app to be in only one file.
However, I might consider a further update with a more modular and maintainable approach.

### How it works
The app can be called from the console or directly by executing the .jar which will launch the app with a GUI.

Once a String password is provided (by pressing the button or by hitting [ENTER] in the password field) it will calculate its hash (SHA-1, matching the external API) and return its hexadecimal representation, providing that to another function that will split it in two parts. The first 5 hexadecimal characters will be sent in the HTTPS request to the external API which will return all hashes having those 5 characters at the beginning (note: it will only return the remaining characters, leaving the first 5 ones that were sent out). Once the response is returned, the app will check if there is any match of the remaining hexadecimal hash characters. If there is any match, it will display how many times that password hash was leaked (provided by the external API). Otherwise, it will tell the user that no matches were found.

The beauty of this approach is that neither the password nor its hash will be sent over the Internet. Only the first 5 characters of the hexadecimal representation of the hash will be sent. I also did an attempt to improve security in my app by setting to null and calling the GC on any value that might contain either the hash or the password itself.

### External API used
Have I Been Pwned API V2: https://haveibeenpwned.com/API/v2

### Java
Java 8 update 201 was used for development and testing.
