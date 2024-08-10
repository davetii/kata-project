conn = new Mongo();
db = conn.getDB("personread-verify");


db.person.insert({
    "_id": "abc123",
    "firstName": "Dave",
    "lastName": "Turner",
    "role": "DEV",
    "email": "davetii2@gmail.com",
    "addr1": "123 nowhere",
    "addr2": "po box 34",
    "city": "harrison",
    "region": "MI",
    "country": "US",
    "phone1": "15868736131",
    "phone2": "15868736132"
});
