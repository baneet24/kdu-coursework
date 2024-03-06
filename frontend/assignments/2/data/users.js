const { v4: uuidv4 } = require("uuid");

let users = [
  {
    id: uuidv4(),
    user_name: "john",
    user_email_id: "john@example.com",
    password: "123",
    profile_url: "https://example.com/profiles/john_doe",
  },
  {
    id: uuidv4(),
    user_name: "alice",
    user_email_id: "alice@example.com",
    password: "alice",
    profile_url: "https://example.com/profiles/alice_smith",
  },
  {
    id: uuidv4(),
    user_name: "bob_jackson",
    user_email_id: "bob@example.com",
    password: "bob_secure_password",
    profile_url: "https://example.com/profiles/bob_jackson",
  },
  {
    id: uuidv4(),
    user_name: "sara_miller",
    user_email_id: "sara@example.com",
    password: "sara_pass123",
    profile_url: "https://example.com/profiles/sara_miller",
  },
  {
    id: uuidv4(),
    user_name: "mike_williams",
    user_email_id: "mike@example.com",
    password: "mike_secret",
    profile_url: "https://example.com/profiles/mike_williams",
  },
];


module.exports = users;
