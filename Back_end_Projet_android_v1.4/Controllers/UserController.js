const createError = require('http-errors')
const User = require('../Models/User.model')
const { authSchema } = require('../helpers/validation_schema')
const {
  signAccessToken,
  signRefreshToken,
  verifyRefreshToken,
} = require('../helpers/jwt_helper')
const client = require('../helpers/init_redis')

module.exports = {
  register: async (req, res, next) => {
    try {
      // const { email, password } = req.body
      // if (!email || !password) throw createError.BadRequest()
      const result = await authSchema.validateAsync(req.body)

      const doesExist = await User.findOne({ email: result.email })
      if (doesExist)
        throw createError.Conflict(`${result.email} is already been registered`)

      const user = new User(result)
      const savedUser = await user.save()
      const accessToken = await signAccessToken(savedUser.id)
      const refreshToken = await signRefreshToken(savedUser.id)

      res.send({ accessToken, refreshToken })
    } catch (error) {
      if (error.isJoi === true) error.status = 422
      next(error)
    }
  },

  login: async (req, res, next) => {
    try {
      const result = await authSchema.validateAsync(req.body)
      const user = await User.findOne({ email: result.email })
      if (!user) throw createError.NotFound('User not registered')

      const isMatch = await user.isValidPassword(result.password)
      if (!isMatch)
        throw createError.Unauthorized('Username or password not valid')

      const accessToken = await signAccessToken(user.id)
      const refreshToken = await signRefreshToken(user.id)

      res.send({ accessToken, refreshToken })
    } catch (error) {
      if (error.isJoi === true)
        return next(createError.BadRequest('Invalid Username or Password'))
      next(error)
    }
  },

  refreshToken: async (req, res, next) => {
    try {
      const { refreshToken } = req.body
      if (!refreshToken) throw createError.BadRequest()
      const userId = await verifyRefreshToken(refreshToken)

      const accessToken = await signAccessToken(userId)
      const refToken = await signRefreshToken(userId)
      res.send({ accessToken: accessToken, refreshToken: refToken })
    } catch (error) {
      next(error)
    }
  },

  logout: async (req, res, next) => {
    try {
      const { refreshToken } = req.body
      if (!refreshToken) throw createError.BadRequest()
      const userId = await verifyRefreshToken(refreshToken)
      client.DEL(userId, (err, val) => {
        if (err) {
          console.log(err.message)
          throw createError.InternalServerError()
        }
        console.log(val)
        res.sendStatus(204)
      })
    } catch (error) {
      next(error)
    }
  },





  EditProfile : async (req, res) =>{


    if (req.files['ProfilePicture']) {
      ProfilePicture = req.files['ProfilePicture'][0]['filename'];
    }
  
    const user = new User({
      
      gender: req.body.gender,
      age: req.body.age,
      aboutme: req.body.aboutme,
      typeanimal: req.body.typeanimal,
      ProfilePicture: ProfilePicture
    });
    try {
      const updateduser = await User.findOneAndUpdate({
        email: req.params.UserEmail
      }, {
        $set: {
          gender: user.gender,
          age: user.age,
          aboutme: user.aboutme,
          typeanimal: user.typeanimal,
          ProfilePicture: user.ProfilePicture
        }
      })
      res.json(user);
    } catch (error) {
      console.log(error)
    }
  
  } ,



  ShowProfile: async  (req, res) => {
    try {
      UserEmail = req.params.UserEmail
      const UserProfile = await User.findOne(User.email);
      res.json(UserProfile);
      console.log(UserProfile)
    } catch (error) {
      res.status(400).json(error);
      console.log(error);
    }
  } ,


  



}
