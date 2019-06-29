# Image Service

Designed an solution for interaction with IMGUR api.

 1. **/register** : Register user by giving username and password as well as basic information like firstname, lastname, email, gender.
 2. **/authenticate** :By providing proper username and password service will generate JWT token which can be used for authoring the subsequent calls. Without JWT token system will not allow for any other calls.
 3. **/profile** : Will provide the logged in user details as well as the details of images the he has association.
 4. **/image** : Uploads the image to imgur api
 5. **/image/{imageid}** :Deletes he image from system.
