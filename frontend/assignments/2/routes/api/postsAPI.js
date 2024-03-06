const express = require("express");
const router = express.Router();
const posts = require("../../data/posts");
const activeUsers = require("../../data/activeUsers")

const { v4: uuidv4 } = require("uuid");


router.get('/:pageNumber/:pageSize',  (req, res) => {
  const pageNumber = Number(req.params.pageNumber);
  const pageSize = Number(req.params.pageSize);
  const start = pageNumber * pageSize;
  const end = start + pageSize;
  const selectedPosts = posts.slice(start, end)
  res.json(selectedPosts);
})

router.post("/", (req, res) => {
  if (!req.body.content) {
    res.status(402).send("Invalid Body");
  }

  try{
  const post = {
    id: uuidv4(),
    content: req.body.content,
    username: req.body.username
  }

  posts.push(post)
  res.status(201).json("Post added successfully");
}
catch(error){
 res.send(error);
}
});



router.get("/", (req, res) => {
 try{
    res.status(201).json(posts);
 }
 catch(error){
res.status(404).send(error);
 }
});

router.get("/:id", (req, res) => {
    const post = posts.filter((post) => post.id === req.params.id);

    if (post.length === 0)
      res.status(404).send("Post with the given id not found");
  
    res.status(200).send(post);
})




module.exports = router;
