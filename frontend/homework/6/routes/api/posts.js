const express = require("express");
const postList = require("../../data/postList");
const router = express.Router();
const { v4: uuidv4 } = require('uuid');

//API to get all posts
router.get('/', (req, res) => {
  if (postList.length == 0) {
    res.status(404).json({ msg: "Sorry, No posts available to show!!" });
  } else {
    res.status(200).json(postList);
  }
});

//API to get a post with given ID
router.get('/:id', (req, res) => {
  const search_id = req.params.id;
  const post = postList.filter(post => post.id === search_id);

  if (post.length === 0) {
    res.status(404).json({
      message: "post not found",
    });
  }

  res.json(post[0]);
});


//API to create new post
router.post('/post', (req, res) => {
    const latestPost = {
      id: uuidv4(),
      name: req.body.name,
      content: req.body.content,
      tags: req.body.tags
    }

    postList.push(latestPost);
    res.status(201).json(latestPost);
});

module.exports = router;