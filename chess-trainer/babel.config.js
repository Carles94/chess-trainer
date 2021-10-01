module.exports = {
    presets: [
      ['@babel/preset-env', {targets: {node: 'current'}}],
     '@babel/preset-typescript',
    ],
    plugins:[
        [require('@babel/plugin-proposal-decorators').default,{ legacy: true }],
        '@babel/plugin-transform-flow-strip-types',
        ['@babel/plugin-proposal-class-properties', {loose: true}],
        ["@babel/plugin-proposal-private-property-in-object", { "loose": true }]
      ],
  };