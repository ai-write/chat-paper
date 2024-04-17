FROM node:18-alpine


ENV NODE_ENV=production

RUN mkdir -p /nuxt3

COPY dist/ . /nuxt3/
WORKDIR /nuxt3
EXPOSE 3000

CMD  ["node","./server/index.mjs"]