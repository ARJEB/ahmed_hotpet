const Joi = require('@hapi/joi')

const authSchema = Joi.object({
  email: Joi.string().email().lowercase().required(),
  password: Joi.string().min(2).required(),
  username: Joi.string().min(2),
})

module.exports = {
  authSchema,
}
