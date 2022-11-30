const express = require('express')
const router = express.Router()
const LikesController = require('../Controllers/LikesController')

router.post('/AddLike/:User_Swiper_param/:User_Swiped_param', LikesController.AddLike)

module.exports = router