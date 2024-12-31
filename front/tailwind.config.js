/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
    "./node_modules/flowbite/**/*.js"
  ],
  theme: {
    extend: {
      animation: {
        'spin-slow': 'spin 20s linear infinite', // Adjust the duration (e.g., 3 seconds)
      },
    },
  },
  plugins: [
    require('daisyui'),
    require('flowbite/plugin')
  ],
  daisyui: {
    themes: [
      {
        mytheme: {
          "primary":"#42bcec",
          "info":"#1c4ed8"
        },
      },
      "dark",
      "cupcake",
    ],
  },
}
