const express = require("express");
const router = express.Router();

const users = require("../../data/users");

router.post("/", (req, res) => {
  if (!req.body.user_name || !req.body.password) {
    res.status(402).json("Invalid credentials");
  }

  const isValidUser = users.some(user => user.user_name === req.body.user_name && user.password === req.body.password);

  if (isValidUser) {
      console.log(`${isValidUser.user_name} is a valid user`)
      res.status(201).json('Logged in successfully');
  } else {
    res.status(402).json('Invalid username or password. Please try again.');
  }
});




module.exports = router;