const createError = require("http-errors");
const User = require("../Models/User.model");
const Likes = require("../Models/Likes.model");

module.exports = {
  AddLike: async (req, res, next) => {
    try {
      const { User_Swiper_param, User_Swiped_param } = req.params;
      console.log(User_Swiper_param, User_Swiped_param);
      const ExistingLike = await Likes.findOne({
        $or: [
          {
            User_Swiper: User_Swiper_param,
            User_Swiped: User_Swiped_param,
          },
          {
            User_Swiper: User_Swiped_param,
            User_Swiped: User_Swiper_param,
          },
        ],
      });
      let createdLike;
      if (ExistingLike) {
        createdLike = await Likes.findByIdAndUpdate(ExistingLike._id, {
          User_Swiped_Right: true,
          Match: true,
        });
      } else {
        const Like = new Likes({
          User_Swiper: User_Swiper_param,
          User_Swiped: User_Swiped_param,
        });
        createdLike = Like.save();
      }
      res.status(200).json(createdLike);
    } catch (error) {
      res.status(500).json({ error: error.message });
    }
  },
};
