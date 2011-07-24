if (jQuery.browser.mozilla) {
  $(function () {
    $('form.cmxform').hide().find('p>label:not(.nocmx):not(.error)').each(function () {
      var $this = $(this);
      var labelContent = $this.html();
      var labelWidth = document.defaultView.getComputedStyle(this, '').getPropertyValue('width');
      var labelSpan = $("<span>").css("display", "block").width(labelWidth).html(labelContent);
      $this.css("display", "-moz-inline-box").empty().append(labelSpan)
    }).end().show()
  })
};
window.PR_SHOULD_USE_CONTINUATION = true;
window.PR_TAB_WIDTH = 8;
window.PR_normalizedHtml = window.PR = window.prettyPrintOne = window.prettyPrint = void 0;
window._pr_isIE6 = function () {
  var y = navigator && navigator.userAgent && navigator.userAgent.match(/\bMSIE ([678])\./);
  y = y ? +y[1] : false;
  window._pr_isIE6 = function () {
    return y
  };
  return y
};
(function () {
  function y(b) {
    return b.replace(L, "&amp;").replace(M, "&lt;").replace(N, "&gt;")
  }
  function H(b, f, i) {
    switch (b.nodeType) {
    case 1:
      var o = b.tagName.toLowerCase();
      f.push("<", o);
      var l = b.attributes,
          n = l.length;
      if (n) {
        if (i) {
          for (var r = [], j = n; --j >= 0;) r[j] = l[j];
          r.sort(function (q, m) {
            return q.name < m.name ? -1 : q.name === m.name ? 0 : 1
          });
          l = r
        }
        for (j = 0; j < n; ++j) {
          r = l[j];
          r.specified && f.push(" ", r.name.toLowerCase(), '="', r.value.replace(L, "&amp;").replace(M, "&lt;").replace(N, "&gt;").replace(X, "&quot;"), '"')
        }
      }
      f.push(">");
      for (l = b.firstChild; l; l = l.nextSibling) H(l, f, i);
      if (b.firstChild || !/^(?:br|link|img)$/.test(o)) f.push("</", o, ">");
      break;
    case 3:
    case 4:
      f.push(y(b.nodeValue));
      break
    }
  }
  function O(b) {
    function f(c) {
      if (c.charAt(0) !== "\\") return c.charCodeAt(0);
      switch (c.charAt(1)) {
      case "b":
        return 8;
      case "t":
        return 9;
      case "n":
        return 10;
      case "v":
        return 11;
      case "f":
        return 12;
      case "r":
        return 13;
      case "u":
      case "x":
        return parseInt(c.substring(2), 16) || c.charCodeAt(1);
      case "0":
      case "1":
      case "2":
      case "3":
      case "4":
      case "5":
      case "6":
      case "7":
        return parseInt(c.substring(1), 8);
      default:
        return c.charCodeAt(1)
      }
    }
    function i(c) {
      if (c < 32) return (c < 16 ? "\\x0" : "\\x") + c.toString(16);
      c = String.fromCharCode(c);
      if (c === "\\" || c === "-" || c === "[" || c === "]") c = "\\" + c;
      return c
    }
    function o(c) {
      var d = c.substring(1, c.length - 1).match(RegExp("\\\\u[0-9A-Fa-f]{4}|\\\\x[0-9A-Fa-f]{2}|\\\\[0-3][0-7]{0,2}|\\\\[0-7]{1,2}|\\\\[\\s\\S]|-|[^-\\\\]", "g"));
      c = [];
      for (var a = [], k = d[0] === "^", e = k ? 1 : 0, h = d.length; e < h; ++e) {
        var g = d[e];
        switch (g) {
        case "\\B":
        case "\\b":
        case "\\D":
        case "\\d":
        case "\\S":
        case "\\s":
        case "\\W":
        case "\\w":
          c.push(g);
          continue
        }
        g = f(g);
        var s;
        if (e + 2 < h && "-" === d[e + 1]) {
          s = f(d[e + 2]);
          e += 2
        }
        else s = g;
        a.push([g, s]);
        if (!(s < 65 || g > 122)) {
          s < 65 || g > 90 || a.push([Math.max(65, g) | 32, Math.min(s, 90) | 32]);
          s < 97 || g > 122 || a.push([Math.max(97, g) & -33, Math.min(s, 122) & -33])
        }
      }
      a.sort(function (v, w) {
        return v[0] - w[0] || w[1] - v[1]
      });
      d = [];
      g = [NaN, NaN];
      for (e = 0; e < a.length; ++e) {
        h = a[e];
        if (h[0] <= g[1] + 1) g[1] = Math.max(g[1], h[1]);
        else d.push(g = h)
      }
      a = ["["];
      k && a.push("^");
      a.push.apply(a, c);
      for (e = 0; e < d.length; ++e) {
        h = d[e];
        a.push(i(h[0]));
        if (h[1] > h[0]) {
          h[1] + 1 > h[0] && a.push("-");
          a.push(i(h[1]))
        }
      }
      a.push("]");
      return a.join("")
    }
    function l(c) {
      for (var d = c.source.match(RegExp("(?:\\[(?:[^\\x5C\\x5D]|\\\\[\\s\\S])*\\]|\\\\u[A-Fa-f0-9]{4}|\\\\x[A-Fa-f0-9]{2}|\\\\[0-9]+|\\\\[^ux0-9]|\\(\\?[:!=]|[\\(\\)\\^]|[^\\x5B\\x5C\\(\\)\\^]+)", "g")), a = d.length, k = [], e = 0, h = 0; e < a; ++e) {
        var g = d[e];
        if (g === "(")++h;
        else if ("\\" === g.charAt(0)) if ((g = +g.substring(1)) && g <= h) k[g] = -1
      }
      for (e = 1; e < k.length; ++e) if (-1 === k[e]) k[e] = ++n;
      for (h = e = 0; e < a; ++e) {
        g = d[e];
        if (g === "(") {
          ++h;
          if (k[h] === undefined) d[e] = "(?:"
        }
        else if ("\\" === g.charAt(0)) if ((g = +g.substring(1)) && g <= h) d[e] = "\\" + k[h]
      }
      for (h = e = 0; e < a; ++e) if ("^" === d[e] && "^" !== d[e + 1]) d[e] = "";
      if (c.ignoreCase && r) for (e = 0; e < a; ++e) {
        g = d[e];
        c = g.charAt(0);
        if (g.length >= 2 && c === "[") d[e] = o(g);
        else if (c !== "\\") d[e] = g.replace(/[a-zA-Z]/g, function (s) {
          s = s.charCodeAt(0);
          return "[" + String.fromCharCode(s & -33, s | 32) + "]"
        })
      }
      return d.join("")
    }
    for (var n = 0, r = false, j = false, q = 0, m = b.length; q < m; ++q) {
      var t = b[q];
      if (t.ignoreCase) j = true;
      else if (/[a-z]/i.test(t.source.replace(/\\u[0-9a-f]{4}|\\x[0-9a-f]{2}|\\[^ux]/gi, ""))) {
        r = true;
        j = false;
        break
      }
    }
    var p = [];
    q = 0;
    for (m = b.length; q < m; ++q) {
      t = b[q];
      if (t.global || t.multiline) throw Error("" + t);
      p.push("(?:" + l(t) + ")")
    }
    return RegExp(p.join("|"), j ? "gi" : "g")
  }
  function Y(b) {
    var f = 0;
    return function (i) {
      for (var o = null, l = 0, n = 0, r = i.length; n < r; ++n) switch (i.charAt(n)) {
      case "\t":
        o || (o = []);
        o.push(i.substring(l, n));
        l = b - f % b;
        for (f += l; l >= 0; l -= 16) o.push("                ".substring(0, l));
        l = n + 1;
        break;
      case "\n":
        f = 0;
        break;
      default:
        ++f
      }
      if (!o) return i;
      o.push(i.substring(l));
      return o.join("")
    }
  }
  function I(b, f, i, o) {
    if (f) {
      b = {
        source: f,
        c: b
      };
      i(b);
      o.push.apply(o, b.d)
    }
  }
  function B(b, f) {
    var i = {},
        o;
    (function () {
      for (var r = b.concat(f), j = [], q = {}, m = 0, t = r.length; m < t; ++m) {
        var p = r[m],
            c = p[3];
        if (c) for (var d = c.length; --d >= 0;) i[c.charAt(d)] = p;
        p = p[1];
        c = "" + p;
        if (!q.hasOwnProperty(c)) {
          j.push(p);
          q[c] = null
        }
      }
      j.push(/[\0-\uffff]/);
      o = O(j)
    })();
    var l = f.length;

    function n(r) {
      for (var j = r.c, q = [j, z], m = 0, t = r.source.match(o) || [], p = {}, c = 0, d = t.length; c < d; ++c) {
        var a = t[c],
            k = p[a],
            e = void 0,
            h;
        if (typeof k === "string") h = false;
        else {
          var g = i[a.charAt(0)];
          if (g) {
            e = a.match(g[1]);
            k = g[0]
          }
          else {
            for (h = 0; h < l; ++h) {
              g = f[h];
              if (e = a.match(g[1])) {
                k = g[0];
                break
              }
            }
            e || (k = z)
          }
          if ((h = k.length >= 5 && "lang-" === k.substring(0, 5)) && !(e && typeof e[1] === "string")) {
            h = false;
            k = P
          }
          h || (p[a] = k)
        }
        g = m;
        m += a.length;
        if (h) {
          h = e[1];
          var s = a.indexOf(h),
              v = s + h.length;
          if (e[2]) {
            v = a.length - e[2].length;
            s = v - h.length
          }
          k = k.substring(5);
          I(j + g, a.substring(0, s), n, q);
          I(j + g + s, h, Q(k, h), q);
          I(j + g + v, a.substring(v), n, q)
        }
        else q.push(j + g, k)
      }
      r.d = q
    }
    return n
  }
  function x(b) {
    var f = [],
        i = [];
    if (b.tripleQuotedStrings) f.push([A, /^(?:\'\'\'(?:[^\'\\]|\\[\s\S]|\'{1,2}(?=[^\']))*(?:\'\'\'|$)|\"\"\"(?:[^\"\\]|\\[\s\S]|\"{1,2}(?=[^\"]))*(?:\"\"\"|$)|\'(?:[^\\\']|\\[\s\S])*(?:\'|$)|\"(?:[^\\\"]|\\[\s\S])*(?:\"|$))/, null, "'\""]);
    else b.multiLineStrings ? f.push([A, /^(?:\'(?:[^\\\']|\\[\s\S])*(?:\'|$)|\"(?:[^\\\"]|\\[\s\S])*(?:\"|$)|\`(?:[^\\\`]|\\[\s\S])*(?:\`|$))/, null, "'\"`"]) : f.push([A, /^(?:\'(?:[^\\\'\r\n]|\\.)*(?:\'|$)|\"(?:[^\\\"\r\n]|\\.)*(?:\"|$))/, null, "\"'"]);
    b.verbatimStrings && i.push([A, /^@\"(?:[^\"]|\"\")*(?:\"|$)/, null]);
    if (b.hashComments) if (b.cStyleComments) {
      f.push([C, /^#(?:(?:define|elif|else|endif|error|ifdef|include|ifndef|line|pragma|undef|warning)\b|[^\r\n]*)/, null, "#"]);
      i.push([A, /^<(?:(?:(?:\.\.\/)*|\/?)(?:[\w-]+(?:\/[\w-]+)+)?[\w-]+\.h|[a-z]\w*)>/, null])
    }
    else f.push([C, /^#[^\r\n]*/, null, "#"]);
    if (b.cStyleComments) {
      i.push([C, /^\/\/[^\r\n]*/, null]);
      i.push([C, /^\/\*[\s\S]*?(?:\*\/|$)/, null])
    }
    b.regexLiterals && i.push(["lang-regex", RegExp("^" + Z + "(/(?=[^/*])(?:[^/\\x5B\\x5C]|\\x5C[\\s\\S]|\\x5B(?:[^\\x5C\\x5D]|\\x5C[\\s\\S])*(?:\\x5D|$))+/)")]);
    b = b.keywords.replace(/^\s+|\s+$/g, "");
    b.length && i.push([R, RegExp("^(?:" + b.replace(/\s+/g, "|") + ")\\b"), null]);
    f.push([z, /^\s+/, null, " \r\n\t\u00a0"]);
    i.push([J, /^@[a-z_$][a-z_$@0-9]*/i, null], [S, /^@?[A-Z]+[a-z][A-Za-z_$@0-9]*/, null], [z, /^[a-z_$][a-z_$@0-9]*/i, null], [J, /^(?:0x[a-f0-9]+|(?:\d(?:_\d+)*\d*(?:\.\d*)?|\.\d\+)(?:e[+\-]?\d+)?)[a-z]*/i, null, "0123456789"], [E, /^.[^\s\w\.$@\'\"\`\/\#]*/, null]);
    return B(f, i)
  }
  function $(b) {
    function f(D) {
      if (D > r) {
        if (j && j !== q) {
          n.push("</span>");
          j = null
        }
        if (!j && q) {
          j = q;
          n.push('<span class="', j, '">')
        }
        var T = y(p(i.substring(r, D))).replace(e ? d : c, "$1&#160;");
        e = k.test(T);
        n.push(T.replace(a, s));
        r = D
      }
    }
    var i = b.source,
        o = b.g,
        l = b.d,
        n = [],
        r = 0,
        j = null,
        q = null,
        m = 0,
        t = 0,
        p = Y(window.PR_TAB_WIDTH),
        c = /([\r\n ]) /g,
        d = /(^| ) /gm,
        a = /\r\n?|\n/g,
        k = /[ \r\n]$/,
        e = true,
        h = window._pr_isIE6();
    h = h ? b.b.tagName === "PRE" ? h === 6 ? "&#160;\r\n" : h === 7 ? "&#160;<br>\r" : "&#160;\r" : "&#160;<br />" : "<br />";
    var g = b.b.className.match(/\blinenums\b(?::(\d+))?/),
        s;
    if (g) {
      for (var v = [], w = 0; w < 10; ++w) v[w] = h + '</li><li class="L' + w + '">';
      var F = g[1] && g[1].length ? g[1] - 1 : 0;
      n.push('<ol class="linenums"><li class="L', F % 10, '"');
      F && n.push(' value="', F + 1, '"');
      n.push(">");
      s = function () {
        var D = v[++F % 10];
        return j ? "</span>" + D + '<span class="' + j + '">' : D
      }
    }
    else s = h;
    for (;;) if (m < o.length ? t < l.length ? o[m] <= l[t] : true : false) {
      f(o[m]);
      if (j) {
        n.push("</span>");
        j = null
      }
      n.push(o[m + 1]);
      m += 2
    }
    else if (t < l.length) {
      f(l[t]);
      q = l[t + 1];
      t += 2
    }
    else break;
    f(i.length);
    j && n.push("</span>");
    g && n.push("</li></ol>");
    b.a = n.join("")
  }
  function u(b, f) {
    for (var i = f.length; --i >= 0;) {
      var o = f[i];
      if (G.hasOwnProperty(o))"console" in window && console.warn("cannot override language handler %s", o);
      else G[o] = b
    }
  }
  function Q(b, f) {
    b && G.hasOwnProperty(b) || (b = /^\s*</.test(f) ? "default-markup" : "default-code");
    return G[b]
  }
  function U(b) {
    var f = b.f,
        i = b.e;
    b.a = f;
    try {
      var o, l = f.match(aa);
      f = [];
      var n = 0,
          r = [];
      if (l) for (var j = 0, q = l.length; j < q; ++j) {
        var m = l[j];
        if (m.length > 1 && m.charAt(0) === "<") {
          if (!ba.test(m)) if (ca.test(m)) {
            f.push(m.substring(9, m.length - 3));
            n += m.length - 12
          }
          else if (da.test(m)) {
            f.push("\n");
            ++n
          }
          else if (m.indexOf(V) >= 0 && m.replace(/\s(\w+)\s*=\s*(?:\"([^\"]*)\"|'([^\']*)'|(\S+))/g, ' $1="$2$3$4"').match(/[cC][lL][aA][sS][sS]=\"[^\"]*\bnocode\b/)) {
            var t = m.match(W)[2],
                p = 1,
                c;
            c = j + 1;
            a: for (; c < q; ++c) {
              var d = l[c].match(W);
              if (d && d[2] === t) if (d[1] === "/") {
                if (--p === 0) break a
              }
              else++p
            }
            if (c < q) {
              r.push(n, l.slice(j, c + 1).join(""));
              j = c
            }
            else r.push(n, m)
          }
          else r.push(n, m)
        }
        else {
          var a;
          p = m;
          var k = p.indexOf("&");
          if (k < 0) a = p;
          else {
            for (--k;
            (k = p.indexOf("&#", k + 1)) >= 0;) {
              var e = p.indexOf(";", k);
              if (e >= 0) {
                var h = p.substring(k + 3, e),
                    g = 10;
                if (h && h.charAt(0) === "x") {
                  h = h.substring(1);
                  g = 16
                }
                var s = parseInt(h, g);
                isNaN(s) || (p = p.substring(0, k) + String.fromCharCode(s) + p.substring(e + 1))
              }
            }
            a = p.replace(ea, "<").replace(fa, ">").replace(ga, "'").replace(ha, '"').replace(ia, " ").replace(ja, "&")
          }
          f.push(a);
          n += a.length
        }
      }
      o = {
        source: f.join(""),
        h: r
      };
      var v = o.source;
      b.source = v;
      b.c = 0;
      b.g = o.h;
      Q(i, v)(b);
      $(b)
    }
    catch (w) {
      if ("console" in window) console.log(w && w.stack ? w.stack : w)
    }
  }
  var A = "str",
      R = "kwd",
      C = "com",
      S = "typ",
      J = "lit",
      E = "pun",
      z = "pln",
      P = "src",
      V = "nocode",
      Z = function () {
      for (var b = ["!", "!=", "!==", "#", "%", "%=", "&", "&&", "&&=", "&=", "(", "*", "*=", "+=", ",", "-=", "->", "/", "/=", ":", "::", ";", "<", "<<", "<<=", "<=", "=", "==", "===", ">", ">=", ">>", ">>=", ">>>", ">>>=", "?", "@", "[", "^", "^=", "^^", "^^=", "{", "|", "|=", "||", "||=", "~", "break", "case", "continue", "delete", "do", "else", "finally", "instanceof", "return", "throw", "try", "typeof"], f = "(?:^^|[+-]", i = 0; i < b.length; ++i) f += "|" + b[i].replace(/([^=<>:&a-z])/g, "\\$1");
      f += ")\\s*";
      return f
      }(),
      L = /&/g,
      M = /</g,
      N = />/g,
      X = /\"/g,
      ea = /&lt;/g,
      fa = /&gt;/g,
      ga = /&apos;/g,
      ha = /&quot;/g,
      ja = /&amp;/g,
      ia = /&nbsp;/g,
      ka = /[\r\n]/g,
      K = null,
      aa = RegExp("[^<]+|<!--[\\s\\S]*?--\>|<!\\[CDATA\\[[\\s\\S]*?\\]\\]>|</?[a-zA-Z](?:[^>\"']|'[^']*'|\"[^\"]*\")*>|<", "g"),
      ba = /^<\!--/,
      ca = /^<!\[CDATA\[/,
      da = /^<br\b/i,
      W = /^<(\/?)([a-zA-Z][a-zA-Z0-9]*)/,
      la = x({
      keywords: "break continue do else for if return while auto case char const default double enum extern float goto int long register short signed sizeof static struct switch typedef union unsigned void volatile catch class delete false import new operator private protected public this throw true try typeof alignof align_union asm axiom bool concept concept_map const_cast constexpr decltype dynamic_cast explicit export friend inline late_check mutable namespace nullptr reinterpret_cast static_assert static_cast template typeid typename using virtual wchar_t where break continue do else for if return while auto case char const default double enum extern float goto int long register short signed sizeof static struct switch typedef union unsigned void volatile catch class delete false import new operator private protected public this throw true try typeof abstract boolean byte extends final finally implements import instanceof null native package strictfp super synchronized throws transient as base by checked decimal delegate descending event fixed foreach from group implicit in interface internal into is lock object out override orderby params partial readonly ref sbyte sealed stackalloc string select uint ulong unchecked unsafe ushort var break continue do else for if return while auto case char const default double enum extern float goto int long register short signed sizeof static struct switch typedef union unsigned void volatile catch class delete false import new operator private protected public this throw true try typeof debugger eval export function get null set undefined var with Infinity NaN caller delete die do dump elsif eval exit foreach for goto if import last local my next no our print package redo require sub undef unless until use wantarray while BEGIN END break continue do else for if return while and as assert class def del elif except exec finally from global import in is lambda nonlocal not or pass print raise try with yield False True None break continue do else for if return while alias and begin case class def defined elsif end ensure false in module next nil not or redo rescue retry self super then true undef unless until when yield BEGIN END break continue do else for if return while case done elif esac eval fi function in local set then until ",
      hashComments: true,
      cStyleComments: true,
      multiLineStrings: true,
      regexLiterals: true
    }),
      G = {};
  u(la, ["default-code"]);
  u(B([], [
    [z, /^[^<?]+/],
    ["dec", /^<!\w[^>]*(?:>|$)/],
    [C, /^<\!--[\s\S]*?(?:-\->|$)/],
    ["lang-", /^<\?([\s\S]+?)(?:\?>|$)/],
    ["lang-", /^<%([\s\S]+?)(?:%>|$)/],
    [E, /^(?:<[%?]|[%?]>)/],
    ["lang-", /^<xmp\b[^>]*>([\s\S]+?)<\/xmp\b[^>]*>/i],
    ["lang-js", /^<script\b[^>]*>([\s\S]*?)(<\/script\b[^>]*>)/i],
    ["lang-css", /^<style\b[^>]*>([\s\S]*?)(<\/style\b[^>]*>)/i],
    ["lang-in.tag", /^(<\/?[a-z][^<>]*>)/i]
  ]), ["default-markup", "htm", "html", "mxml", "xhtml", "xml", "xsl"]);
  u(B([
    [z, /^[\s]+/, null, " \t\r\n"],
    ["atv", /^(?:\"[^\"]*\"?|\'[^\']*\'?)/, null, "\"'"]
  ], [
    ["tag", /^^<\/?[a-z](?:[\w.:-]*\w)?|\/?>$/i],
    ["atn", /^(?!style[\s=]|on)[a-z](?:[\w:-]*\w)?/i],
    ["lang-uq.val", /^=\s*([^>\'\"\s]*(?:[^>\'\"\s\/]|\/(?=\s)))/],
    [E, /^[=<>\/]+/],
    ["lang-js", /^on\w+\s*=\s*\"([^\"]+)\"/i],
    ["lang-js", /^on\w+\s*=\s*\'([^\']+)\'/i],
    ["lang-js", /^on\w+\s*=\s*([^\"\'>\s]+)/i],
    ["lang-css", /^style\s*=\s*\"([^\"]+)\"/i],
    ["lang-css", /^style\s*=\s*\'([^\']+)\'/i],
    ["lang-css", /^style\s*=\s*([^\"\'>\s]+)/i]
  ]), ["in.tag"]);
  u(B([], [
    ["atv", /^[\s\S]+/]
  ]), ["uq.val"]);
  u(x({
    keywords: "break continue do else for if return while auto case char const default double enum extern float goto int long register short signed sizeof static struct switch typedef union unsigned void volatile catch class delete false import new operator private protected public this throw true try typeof alignof align_union asm axiom bool concept concept_map const_cast constexpr decltype dynamic_cast explicit export friend inline late_check mutable namespace nullptr reinterpret_cast static_assert static_cast template typeid typename using virtual wchar_t where ",
    hashComments: true,
    cStyleComments: true
  }), ["c", "cc", "cpp", "cxx", "cyc", "m"]);
  u(x({
    keywords: "null true false"
  }), ["json"]);
  u(x({
    keywords: "break continue do else for if return while auto case char const default double enum extern float goto int long register short signed sizeof static struct switch typedef union unsigned void volatile catch class delete false import new operator private protected public this throw true try typeof abstract boolean byte extends final finally implements import instanceof null native package strictfp super synchronized throws transient as base by checked decimal delegate descending event fixed foreach from group implicit in interface internal into is lock object out override orderby params partial readonly ref sbyte sealed stackalloc string select uint ulong unchecked unsafe ushort var ",
    hashComments: true,
    cStyleComments: true,
    verbatimStrings: true
  }), ["cs"]);
  u(x({
    keywords: "break continue do else for if return while auto case char const default double enum extern float goto int long register short signed sizeof static struct switch typedef union unsigned void volatile catch class delete false import new operator private protected public this throw true try typeof abstract boolean byte extends final finally implements import instanceof null native package strictfp super synchronized throws transient ",
    cStyleComments: true
  }), ["java"]);
  u(x({
    keywords: "break continue do else for if return while case done elif esac eval fi function in local set then until ",
    hashComments: true,
    multiLineStrings: true
  }), ["bsh", "csh", "sh"]);
  u(x({
    keywords: "break continue do else for if return while and as assert class def del elif except exec finally from global import in is lambda nonlocal not or pass print raise try with yield False True None ",
    hashComments: true,
    multiLineStrings: true,
    tripleQuotedStrings: true
  }), ["cv", "py"]);
  u(x({
    keywords: "caller delete die do dump elsif eval exit foreach for goto if import last local my next no our print package redo require sub undef unless until use wantarray while BEGIN END ",
    hashComments: true,
    multiLineStrings: true,
    regexLiterals: true
  }), ["perl", "pl", "pm"]);
  u(x({
    keywords: "break continue do else for if return while alias and begin case class def defined elsif end ensure false in module next nil not or redo rescue retry self super then true undef unless until when yield BEGIN END ",
    hashComments: true,
    multiLineStrings: true,
    regexLiterals: true
  }), ["rb"]);
  u(x({
    keywords: "break continue do else for if return while auto case char const default double enum extern float goto int long register short signed sizeof static struct switch typedef union unsigned void volatile catch class delete false import new operator private protected public this throw true try typeof debugger eval export function get null set undefined var with Infinity NaN ",
    cStyleComments: true,
    regexLiterals: true
  }), ["js"]);
  u(B([], [
    [A, /^[\s\S]+/]
  ]), ["regex"]);
  window.PR_normalizedHtml = H;
  window.prettyPrintOne = function (b, f) {
    var i = {
      f: b,
      e: f
    };
    U(i);
    return i.a
  };
  window.prettyPrint = function (b) {
    function f() {
      for (var t = window.PR_SHOULD_USE_CONTINUATION ? j.now() + 250 : Infinity; q < o.length && j.now() < t; q++) {
        var p = o[q];
        if (p.className && p.className.indexOf("prettyprint") >= 0) {
          var c = p.className.match(/\blang-(\w+)\b/);
          if (c) c = c[1];
          for (var d = false, a = p.parentNode; a; a = a.parentNode) if ((a.tagName === "pre" || a.tagName === "code" || a.tagName === "xmp") && a.className && a.className.indexOf("prettyprint") >= 0) {
            d = true;
            break
          }
          if (!d) {
            a = p;
            if (null === K) {
              d = document.createElement("PRE");
              d.appendChild(document.createTextNode('<!DOCTYPE foo PUBLIC "foo bar">\n<foo />'));
              K = !/</.test(d.innerHTML)
            }
            if (K) {
              d = a.innerHTML;
              if ("XMP" === a.tagName) d = y(d);
              else {
                a = a;
                if ("PRE" === a.tagName) a = true;
                else if (ka.test(d)) {
                  var k = "";
                  if (a.currentStyle) k = a.currentStyle.whiteSpace;
                  else if (window.getComputedStyle) k = window.getComputedStyle(a, null).whiteSpace;
                  a = !k || k === "pre"
                }
                else a = true;
                a || (d = d.replace(/(<br\s*\/?>)[\r\n]+/g, "$1").replace(/(?:[\r\n]+[ \t]*)+/g, " "))
              }
              d = d
            }
            else {
              d = [];
              for (a = a.firstChild; a; a = a.nextSibling) H(a, d);
              d = d.join("")
            }
            d = d.replace(/(?:\r\n?|\n)$/, "");
            m = {
              f: d,
              e: c,
              b: p
            };
            U(m);
            if (p = m.a) {
              c = m.b;
              if ("XMP" === c.tagName) {
                d = document.createElement("PRE");
                for (a = 0; a < c.attributes.length; ++a) {
                  k = c.attributes[a];
                  if (k.specified) if (k.name.toLowerCase() === "class") d.className = k.value;
                  else d.setAttribute(k.name, k.value)
                }
                d.innerHTML = p;
                c.parentNode.replaceChild(d, c)
              }
              else c.innerHTML = p
            }
          }
        }
      }
      if (q < o.length) setTimeout(f, 250);
      else b && b()
    }
    for (var i = [document.getElementsByTagName("pre"), document.getElementsByTagName("code"), document.getElementsByTagName("xmp")], o = [], l = 0; l < i.length; ++l) for (var n = 0, r = i[l].length; n < r; ++n) o.push(i[l][n]);
    i = null;
    var j = Date;
    j.now || (j = {
      now: function () {
        return (new Date).getTime()
      }
    });
    var q = 0,
        m;
    f()
  };
  window.PR = {
    combinePrefixPatterns: O,
    createSimpleLexer: B,
    registerLangHandler: u,
    sourceDecorator: x,
    PR_ATTRIB_NAME: "atn",
    PR_ATTRIB_VALUE: "atv",
    PR_COMMENT: C,
    PR_DECLARATION: "dec",
    PR_KEYWORD: R,
    PR_LITERAL: J,
    PR_NOCODE: V,
    PR_PLAIN: z,
    PR_PUNCTUATION: E,
    PR_SOURCE: P,
    PR_STRING: A,
    PR_TAG: "tag",
    PR_TYPE: S
  }
})();
(function ($) {
  $.fn.spasticNav = function (options) {
    options = $.extend({
      overlap: 20,
      speed: 500,
      reset: 1500,
      color: '#0b2b61',
      easing: 'easeOutExpo'
    }, options);
    return this.each(function () {
      var nav = $(this),
          currentPageItem = $('#selected', nav),
          blob, reset;
      $('<li id="blob"></li>').css({
        width: currentPageItem.outerWidth(),
        height: currentPageItem.outerHeight() + options.overlap,
        left: currentPageItem.position().left,
        top: currentPageItem.position().top - options.overlap / 2,
        backgroundColor: options.color
      }).appendTo(this);
      blob = $('#blob', nav);
      $('li:not(#blob)', nav).hover(function () {
        clearTimeout(reset);
        blob.animate({
          left: $(this).position().left,
          width: $(this).width()
        }, {
          duration: options.speed,
          easing: options.easing,
          queue: false
        })
      }, function () {
        reset = setTimeout(function () {
          blob.animate({
            width: currentPageItem.outerWidth(),
            left: currentPageItem.position().left
          }, options.speed)
        }, options.reset)
      })
    })
  }
})(jQuery);
(function ($) {
  $.fn.updnWatermark = function (options) {
    options = $.extend({}, $.fn.updnWatermark.defaults, options);
    return this.each(function () {
      var $input = $(this);
      var $watermark = $input.data("updnWatermark");
      if (!$watermark && this.title) {
        var $watermark = $("<span/>").addClass(options.cssClass).insertBefore(this).hide().bind("show", function () {
          $(this).children().fadeIn("fast")
        }).bind("hide", function () {
          $(this).children().hide()
        });
        $("<label/>").appendTo($watermark).text(this.title).attr("for", this.id);
        $input.data("updnWatermark", $watermark)
      }
      if ($watermark) {
        $input.focus(function (ev) {
          $watermark.trigger("hide")
        }).blur(function (ev) {
          if (!$(this).val()) {
            $watermark.trigger("show")
          }
        });
        if (!$input.val()) {
          $watermark.show()
        }
      }
    })
  };
  $.fn.updnWatermark.defaults = {
    cssClass: "updnWatermark"
  };
  $.updnWatermark = {
    attachAll: function (options) {
      $("input:text[title!=''],input:password[title!=''],textarea[title!='']").updnWatermark(options)
    }
  }
})(jQuery);
(function (c) {
  c.extend(c.fn, {
    validate: function (a) {
      if (this.length) {
        var b = c.data(this[0], "validator");
        if (b) return b;
        b = new c.validator(a, this[0]);
        c.data(this[0], "validator", b);
        if (b.settings.onsubmit) {
          this.find("input, button").filter(".cancel").click(function () {
            b.cancelSubmit = true
          });
          b.settings.submitHandler && this.find("input, button").filter(":submit").click(function () {
            b.submitButton = this
          });
          this.submit(function (d) {
            function e() {
              if (b.settings.submitHandler) {
                if (b.submitButton) var f = c("<input type='hidden'/>").attr("name", b.submitButton.name).val(b.submitButton.value).appendTo(b.currentForm);
                b.settings.submitHandler.call(b, b.currentForm);
                b.submitButton && f.remove();
                return false
              }
              return true
            }
            b.settings.debug && d.preventDefault();
            if (b.cancelSubmit) {
              b.cancelSubmit = false;
              return e()
            }
            if (b.form()) {
              if (b.pendingRequest) {
                b.formSubmitted = true;
                return false
              }
              return e()
            }
            else {
              b.focusInvalid();
              return false
            }
          })
        }
        return b
      }
      else a && a.debug && window.console && console.warn("nothing selected, can't validate, returning nothing")
    },
    valid: function () {
      if (c(this[0]).is("form")) return this.validate().form();
      else {
        var a = true,
            b = c(this[0].form).validate();
        this.each(function () {
          a &= b.element(this)
        });
        return a
      }
    },
    removeAttrs: function (a) {
      var b = {},
          d = this;
      c.each(a.split(/\s/), function (e, f) {
        b[f] = d.attr(f);
        d.removeAttr(f)
      });
      return b
    },
    rules: function (a, b) {
      var d = this[0];
      if (a) {
        var e = c.data(d.form, "validator").settings,
            f = e.rules,
            g = c.validator.staticRules(d);
        switch (a) {
        case "add":
          c.extend(g, c.validator.normalizeRule(b));
          f[d.name] = g;
          if (b.messages) e.messages[d.name] = c.extend(e.messages[d.name], b.messages);
          break;
        case "remove":
          if (!b) {
            delete f[d.name];
            return g
          }
          var h = {};
          c.each(b.split(/\s/), function (j, i) {
            h[i] = g[i];
            delete g[i]
          });
          return h
        }
      }
      d = c.validator.normalizeRules(c.extend({}, c.validator.metadataRules(d), c.validator.classRules(d), c.validator.attributeRules(d), c.validator.staticRules(d)), d);
      if (d.required) {
        e = d.required;
        delete d.required;
        d = c.extend({
          required: e
        }, d)
      }
      return d
    }
  });
  c.extend(c.expr[":"], {
    blank: function (a) {
      return !c.trim("" + a.value)
    },
    filled: function (a) {
      return !!c.trim("" + a.value)
    },
    unchecked: function (a) {
      return !a.checked
    }
  });
  c.validator = function (a, b) {
    this.settings = c.extend(true, {}, c.validator.defaults, a);
    this.currentForm = b;
    this.init()
  };
  c.validator.format = function (a, b) {
    if (arguments.length == 1) return function () {
      var d = c.makeArray(arguments);
      d.unshift(a);
      return c.validator.format.apply(this, d)
    };
    if (arguments.length > 2 && b.constructor != Array) b = c.makeArray(arguments).slice(1);
    if (b.constructor != Array) b = [b];
    c.each(b, function (d, e) {
      a = a.replace(RegExp("\\{" + d + "\\}", "g"), e)
    });
    return a
  };
  c.extend(c.validator, {
    defaults: {
      messages: {},
      groups: {},
      rules: {},
      errorClass: "error",
      validClass: "valid",
      errorElement: "label",
      focusInvalid: true,
      errorContainer: c([]),
      errorLabelContainer: c([]),
      onsubmit: true,
      ignore: [],
      ignoreTitle: false,
      onfocusin: function (a) {
        this.lastActive = a;
        if (this.settings.focusCleanup && !this.blockFocusCleanup) {
          this.settings.unhighlight && this.settings.unhighlight.call(this, a, this.settings.errorClass, this.settings.validClass);
          this.addWrapper(this.errorsFor(a)).hide()
        }
      },
      onfocusout: function (a) {
        if (!this.checkable(a) && (a.name in this.submitted || !this.optional(a))) this.element(a)
      },
      onkeyup: function (a) {
        if (a.name in this.submitted || a == this.lastElement) this.element(a)
      },
      onclick: function (a) {
        if (a.name in this.submitted) this.element(a);
        else a.parentNode.name in this.submitted && this.element(a.parentNode)
      },
      highlight: function (a, b, d) {
        c(a).addClass(b).removeClass(d)
      },
      unhighlight: function (a, b, d) {
        c(a).removeClass(b).addClass(d)
      }
    },
    setDefaults: function (a) {
      c.extend(c.validator.defaults, a)
    },
    messages: {
      required: "This field is required.",
      remote: "Please fix this field.",
      email: "Please enter a valid email address.",
      url: "Please enter a valid URL.",
      date: "Please enter a valid date.",
      dateISO: "Please enter a valid date (ISO).",
      number: "Please enter a valid number.",
      digits: "Please enter only digits.",
      creditcard: "Please enter a valid credit card number.",
      equalTo: "Please enter the same value again.",
      accept: "Please enter a value with a valid extension.",
      maxlength: c.validator.format("Please enter no more than {0} characters."),
      minlength: c.validator.format("Please enter at least {0} characters."),
      rangelength: c.validator.format("Please enter a value between {0} and {1} characters long."),
      range: c.validator.format("Please enter a value between {0} and {1}."),
      max: c.validator.format("Please enter a value less than or equal to {0}."),
      min: c.validator.format("Please enter a value greater than or equal to {0}.")
    },
    autoCreateRanges: false,
    prototype: {
      init: function () {
        function a(e) {
          var f = c.data(this[0].form, "validator");
          e = "on" + e.type.replace(/^validate/, "");
          f.settings[e] && f.settings[e].call(f, this[0])
        }
        this.labelContainer = c(this.settings.errorLabelContainer);
        this.errorContext = this.labelContainer.length && this.labelContainer || c(this.currentForm);
        this.containers = c(this.settings.errorContainer).add(this.settings.errorLabelContainer);
        this.submitted = {};
        this.valueCache = {};
        this.pendingRequest = 0;
        this.pending = {};
        this.invalid = {};
        this.reset();
        var b = this.groups = {};
        c.each(this.settings.groups, function (e, f) {
          c.each(f.split(/\s/), function (g, h) {
            b[h] = e
          })
        });
        var d = this.settings.rules;
        c.each(d, function (e, f) {
          d[e] = c.validator.normalizeRule(f)
        });
        c(this.currentForm).validateDelegate(":text, :password, :file, select, textarea", "focusin focusout keyup", a).validateDelegate(":radio, :checkbox, select, option", "click", a);
        this.settings.invalidHandler && c(this.currentForm).bind("invalid-form.validate", this.settings.invalidHandler)
      },
      form: function () {
        this.checkForm();
        c.extend(this.submitted, this.errorMap);
        this.invalid = c.extend({}, this.errorMap);
        this.valid() || c(this.currentForm).triggerHandler("invalid-form", [this]);
        this.showErrors();
        return this.valid()
      },
      checkForm: function () {
        this.prepareForm();
        for (var a = 0, b = this.currentElements = this.elements(); b[a]; a++) this.check(b[a]);
        return this.valid()
      },
      element: function (a) {
        this.lastElement = a = this.clean(a);
        this.prepareElement(a);
        this.currentElements = c(a);
        var b = this.check(a);
        if (b) delete this.invalid[a.name];
        else this.invalid[a.name] = true;
        if (!this.numberOfInvalids()) this.toHide = this.toHide.add(this.containers);
        this.showErrors();
        return b
      },
      showErrors: function (a) {
        if (a) {
          c.extend(this.errorMap, a);
          this.errorList = [];
          for (var b in a) this.errorList.push({
            message: a[b],
            element: this.findByName(b)[0]
          });
          this.successList = c.grep(this.successList, function (d) {
            return !(d.name in a)
          })
        }
        this.settings.showErrors ? this.settings.showErrors.call(this, this.errorMap, this.errorList) : this.defaultShowErrors()
      },
      resetForm: function () {
        c.fn.resetForm && c(this.currentForm).resetForm();
        this.submitted = {};
        this.prepareForm();
        this.hideErrors();
        this.elements().removeClass(this.settings.errorClass)
      },
      numberOfInvalids: function () {
        return this.objectLength(this.invalid)
      },
      objectLength: function (a) {
        var b = 0,
            d;
        for (d in a) b++;
        return b
      },
      hideErrors: function () {
        this.addWrapper(this.toHide).hide()
      },
      valid: function () {
        return this.size() == 0
      },
      size: function () {
        return this.errorList.length
      },
      focusInvalid: function () {
        if (this.settings.focusInvalid) try {
          c(this.findLastActive() || this.errorList.length && this.errorList[0].element || []).filter(":visible").focus().trigger("focusin")
        }
        catch (a) {}
      },
      findLastActive: function () {
        var a = this.lastActive;
        return a && c.grep(this.errorList, function (b) {
          return b.element.name == a.name
        }).length == 1 && a
      },
      elements: function () {
        var a = this,
            b = {};
        return c([]).add(this.currentForm.elements).filter(":input").not(":submit, :reset, :image, [disabled]").not(this.settings.ignore).filter(function () {
          !this.name && a.settings.debug && window.console && console.error("%o has no name assigned", this);
          if (this.name in b || !a.objectLength(c(this).rules())) return false;
          return b[this.name] = true
        })
      },
      clean: function (a) {
        return c(a)[0]
      },
      errors: function () {
        return c(this.settings.errorElement + "." + this.settings.errorClass, this.errorContext)
      },
      reset: function () {
        this.successList = [];
        this.errorList = [];
        this.errorMap = {};
        this.toShow = c([]);
        this.toHide = c([]);
        this.currentElements = c([])
      },
      prepareForm: function () {
        this.reset();
        this.toHide = this.errors().add(this.containers)
      },
      prepareElement: function (a) {
        this.reset();
        this.toHide = this.errorsFor(a)
      },
      check: function (a) {
        a = this.clean(a);
        if (this.checkable(a)) a = this.findByName(a.name).not(this.settings.ignore)[0];
        var b = c(a).rules(),
            d = false,
            e;
        for (e in b) {
          var f = {
            method: e,
            parameters: b[e]
          };
          try {
            var g = c.validator.methods[e].call(this, a.value.replace(/\r/g, ""), a, f.parameters);
            if (g == "dependency-mismatch") d = true;
            else {
              d = false;
              if (g == "pending") {
                this.toHide = this.toHide.not(this.errorsFor(a));
                return
              }
              if (!g) {
                this.formatAndAdd(a, f);
                return false
              }
            }
          }
          catch (h) {
            this.settings.debug && window.console && console.log("exception occured when checking element " + a.id + ", check the '" + f.method + "' method", h);
            throw h;
          }
        }
        if (!d) {
          this.objectLength(b) && this.successList.push(a);
          return true
        }
      },
      customMetaMessage: function (a, b) {
        if (c.metadata) {
          var d = this.settings.meta ? c(a).metadata()[this.settings.meta] : c(a).metadata();
          return d && d.messages && d.messages[b]
        }
      },
      customMessage: function (a, b) {
        var d = this.settings.messages[a];
        return d && (d.constructor == String ? d : d[b])
      },
      findDefined: function () {
        for (var a = 0; a < arguments.length; a++) if (arguments[a] !== undefined) return arguments[a]
      },
      defaultMessage: function (a, b) {
        return this.findDefined(this.customMessage(a.name, b), this.customMetaMessage(a, b), !this.settings.ignoreTitle && a.title || undefined, c.validator.messages[b], "<strong>Warning: No message defined for " + a.name + "</strong>")
      },
      formatAndAdd: function (a, b) {
        var d = this.defaultMessage(a, b.method),
            e = /\$?\{(\d+)\}/g;
        if (typeof d == "function") d = d.call(this, b.parameters, a);
        else if (e.test(d)) d = jQuery.format(d.replace(e, "{$1}"), b.parameters);
        this.errorList.push({
          message: d,
          element: a
        });
        this.errorMap[a.name] = d;
        this.submitted[a.name] = d
      },
      addWrapper: function (a) {
        if (this.settings.wrapper) a = a.add(a.parent(this.settings.wrapper));
        return a
      },
      defaultShowErrors: function () {
        for (var a = 0; this.errorList[a]; a++) {
          var b = this.errorList[a];
          this.settings.highlight && this.settings.highlight.call(this, b.element, this.settings.errorClass, this.settings.validClass);
          this.showLabel(b.element, b.message)
        }
        if (this.errorList.length) this.toShow = this.toShow.add(this.containers);
        if (this.settings.success) for (a = 0; this.successList[a]; a++) this.showLabel(this.successList[a]);
        if (this.settings.unhighlight) {
          a = 0;
          for (b = this.validElements(); b[a]; a++) this.settings.unhighlight.call(this, b[a], this.settings.errorClass, this.settings.validClass)
        }
        this.toHide = this.toHide.not(this.toShow);
        this.hideErrors();
        this.addWrapper(this.toShow).show()
      },
      validElements: function () {
        return this.currentElements.not(this.invalidElements())
      },
      invalidElements: function () {
        return c(this.errorList).map(function () {
          return this.element
        })
      },
      showLabel: function (a, b) {
        var d = this.errorsFor(a);
        if (d.length) {
          d.removeClass().addClass(this.settings.errorClass);
          d.attr("generated") && d.html(b)
        }
        else {
          d = c("<" + this.settings.errorElement + "/>").attr({
            "for": this.idOrName(a),
            generated: true
          }).addClass(this.settings.errorClass).html(b || "");
          if (this.settings.wrapper) d = d.hide().show().wrap("<" + this.settings.wrapper + "/>").parent();
          this.labelContainer.append(d).length || (this.settings.errorPlacement ? this.settings.errorPlacement(d, c(a)) : d.insertAfter(a))
        }
        if (!b && this.settings.success) {
          d.text("");
          typeof this.settings.success == "string" ? d.addClass(this.settings.success) : this.settings.success(d)
        }
        this.toShow = this.toShow.add(d)
      },
      errorsFor: function (a) {
        var b = this.idOrName(a);
        return this.errors().filter(function () {
          return c(this).attr("for") == b
        })
      },
      idOrName: function (a) {
        return this.groups[a.name] || (this.checkable(a) ? a.name : a.id || a.name)
      },
      checkable: function (a) {
        return /radio|checkbox/i.test(a.type)
      },
      findByName: function (a) {
        var b = this.currentForm;
        return c(document.getElementsByName(a)).map(function (d, e) {
          return e.form == b && e.name == a && e || null
        })
      },
      getLength: function (a, b) {
        switch (b.nodeName.toLowerCase()) {
        case "select":
          return c("option:selected", b).length;
        case "input":
          if (this.checkable(b)) return this.findByName(b.name).filter(":checked").length
        }
        return a.length
      },
      depend: function (a, b) {
        return this.dependTypes[typeof a] ? this.dependTypes[typeof a](a, b) : true
      },
      dependTypes: {
        "boolean": function (a) {
          return a
        },
        string: function (a, b) {
          return !!c(a, b.form).length
        },
        "function": function (a, b) {
          return a(b)
        }
      },
      optional: function (a) {
        return !c.validator.methods.required.call(this, c.trim(a.value), a) && "dependency-mismatch"
      },
      startRequest: function (a) {
        if (!this.pending[a.name]) {
          this.pendingRequest++;
          this.pending[a.name] = true
        }
      },
      stopRequest: function (a, b) {
        this.pendingRequest--;
        if (this.pendingRequest < 0) this.pendingRequest = 0;
        delete this.pending[a.name];
        if (b && this.pendingRequest == 0 && this.formSubmitted && this.form()) {
          c(this.currentForm).submit();
          this.formSubmitted = false
        }
        else if (!b && this.pendingRequest == 0 && this.formSubmitted) {
          c(this.currentForm).triggerHandler("invalid-form", [this]);
          this.formSubmitted = false
        }
      },
      previousValue: function (a) {
        return c.data(a, "previousValue") || c.data(a, "previousValue", {
          old: null,
          valid: true,
          message: this.defaultMessage(a, "remote")
        })
      }
    },
    classRuleSettings: {
      required: {
        required: true
      },
      email: {
        email: true
      },
      url: {
        url: true
      },
      date: {
        date: true
      },
      dateISO: {
        dateISO: true
      },
      dateDE: {
        dateDE: true
      },
      number: {
        number: true
      },
      numberDE: {
        numberDE: true
      },
      digits: {
        digits: true
      },
      creditcard: {
        creditcard: true
      }
    },
    addClassRules: function (a, b) {
      a.constructor == String ? this.classRuleSettings[a] = b : c.extend(this.classRuleSettings, a)
    },
    classRules: function (a) {
      var b = {};
      (a = c(a).attr("class")) && c.each(a.split(" "), function () {
        this in c.validator.classRuleSettings && c.extend(b, c.validator.classRuleSettings[this])
      });
      return b
    },
    attributeRules: function (a) {
      var b = {};
      a = c(a);
      for (var d in c.validator.methods) {
        var e = a.attr(d);
        if (e) b[d] = e
      }
      b.maxlength && /-1|2147483647|524288/.test(b.maxlength) && delete b.maxlength;
      return b
    },
    metadataRules: function (a) {
      if (!c.metadata) return {};
      var b = c.data(a.form, "validator").settings.meta;
      return b ? c(a).metadata()[b] : c(a).metadata()
    },
    staticRules: function (a) {
      var b = {},
          d = c.data(a.form, "validator");
      if (d.settings.rules) b = c.validator.normalizeRule(d.settings.rules[a.name]) || {};
      return b
    },
    normalizeRules: function (a, b) {
      c.each(a, function (d, e) {
        if (e === false) delete a[d];
        else if (e.param || e.depends) {
          var f = true;
          switch (typeof e.depends) {
          case "string":
            f = !! c(e.depends, b.form).length;
            break;
          case "function":
            f = e.depends.call(b, b)
          }
          if (f) a[d] = e.param !== undefined ? e.param : true;
          else delete a[d]
        }
      });
      c.each(a, function (d, e) {
        a[d] = c.isFunction(e) ? e(b) : e
      });
      c.each(["minlength", "maxlength", "min", "max"], function () {
        if (a[this]) a[this] = Number(a[this])
      });
      c.each(["rangelength", "range"], function () {
        if (a[this]) a[this] = [Number(a[this][0]), Number(a[this][1])]
      });
      if (c.validator.autoCreateRanges) {
        if (a.min && a.max) {
          a.range = [a.min, a.max];
          delete a.min;
          delete a.max
        }
        if (a.minlength && a.maxlength) {
          a.rangelength = [a.minlength, a.maxlength];
          delete a.minlength;
          delete a.maxlength
        }
      }
      a.messages && delete a.messages;
      return a
    },
    normalizeRule: function (a) {
      if (typeof a == "string") {
        var b = {};
        c.each(a.split(/\s/), function () {
          b[this] = true
        });
        a = b
      }
      return a
    },
    addMethod: function (a, b, d) {
      c.validator.methods[a] = b;
      c.validator.messages[a] = d != undefined ? d : c.validator.messages[a];
      b.length < 3 && c.validator.addClassRules(a, c.validator.normalizeRule(a))
    },
    methods: {
      required: function (a, b, d) {
        if (!this.depend(d, b)) return "dependency-mismatch";
        switch (b.nodeName.toLowerCase()) {
        case "select":
          return (a = c(b).val()) && a.length > 0;
        case "input":
          if (this.checkable(b)) return this.getLength(a, b) > 0;
        default:
          return c.trim(a).length > 0
        }
      },
      remote: function (a, b, d) {
        if (this.optional(b)) return "dependency-mismatch";
        var e = this.previousValue(b);
        this.settings.messages[b.name] || (this.settings.messages[b.name] = {});
        e.originalMessage = this.settings.messages[b.name].remote;
        this.settings.messages[b.name].remote = e.message;
        d = typeof d == "string" && {
          url: d
        } || d;
        if (this.pending[b.name]) return "pending";
        if (e.old === a) return e.valid;
        e.old = a;
        var f = this;
        this.startRequest(b);
        var g = {};
        g[b.name] = a;
        c.ajax(c.extend(true, {
          url: d,
          mode: "abort",
          port: "validate" + b.name,
          dataType: "json",
          data: g,
          success: function (h) {
            f.settings.messages[b.name].remote = e.originalMessage;
            var j = h === true;
            if (j) {
              var i = f.formSubmitted;
              f.prepareElement(b);
              f.formSubmitted = i;
              f.successList.push(b);
              f.showErrors()
            }
            else {
              i = {};
              h = h || f.defaultMessage(b, "remote");
              i[b.name] = e.message = c.isFunction(h) ? h(a) : h;
              f.showErrors(i)
            }
            e.valid = j;
            f.stopRequest(b, j)
          }
        }, d));
        return "pending"
      },
      minlength: function (a, b, d) {
        return this.optional(b) || this.getLength(c.trim(a), b) >= d
      },
      maxlength: function (a, b, d) {
        return this.optional(b) || this.getLength(c.trim(a), b) <= d
      },
      rangelength: function (a, b, d) {
        a = this.getLength(c.trim(a), b);
        return this.optional(b) || a >= d[0] && a <= d[1]
      },
      min: function (a, b, d) {
        return this.optional(b) || a >= d
      },
      max: function (a, b, d) {
        return this.optional(b) || a <= d
      },
      range: function (a, b, d) {
        return this.optional(b) || a >= d[0] && a <= d[1]
      },
      email: function (a, b) {
        return this.optional(b) || /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(a)
      },
      url: function (a, b) {
        return this.optional(b) || /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(a)
      },
      date: function (a, b) {
        return this.optional(b) || !/Invalid|NaN/.test(new Date(a))
      },
      dateISO: function (a, b) {
        return this.optional(b) || /^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/.test(a)
      },
      number: function (a, b) {
        return this.optional(b) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(a)
      },
      digits: function (a, b) {
        return this.optional(b) || /^\d+$/.test(a)
      },
      creditcard: function (a, b) {
        if (this.optional(b)) return "dependency-mismatch";
        if (/[^0-9-]+/.test(a)) return false;
        var d = 0,
            e = 0,
            f = false;
        a = a.replace(/\D/g, "");
        for (var g = a.length - 1; g >= 0; g--) {
          e = a.charAt(g);
          e = parseInt(e, 10);
          if (f) if ((e *= 2) > 9) e -= 9;
          d += e;
          f = !f
        }
        return d % 10 == 0
      },
      accept: function (a, b, d) {
        d = typeof d == "string" ? d.replace(/,/g, "|") : "png|jpe?g|gif";
        return this.optional(b) || a.match(RegExp(".(" + d + ")$", "i"))
      },
      equalTo: function (a, b, d) {
        d = c(d).unbind(".validate-equalTo").bind("blur.validate-equalTo", function () {
          c(b).valid()
        });
        return a == d.val()
      }
    }
  });
  c.format = c.validator.format
})(jQuery);
(function (c) {
  var a = {};
  if (c.ajaxPrefilter) c.ajaxPrefilter(function (d, e, f) {
    e = d.port;
    if (d.mode == "abort") {
      a[e] && a[e].abort();
      a[e] = f
    }
  });
  else {
    var b = c.ajax;
    c.ajax = function (d) {
      var e = ("port" in d ? d : c.ajaxSettings).port;
      if (("mode" in d ? d : c.ajaxSettings).mode == "abort") {
        a[e] && a[e].abort();
        return a[e] = b.apply(this, arguments)
      }
      return b.apply(this, arguments)
    }
  }
})(jQuery);
(function (c) {
  !jQuery.event.special.focusin && !jQuery.event.special.focusout && document.addEventListener && c.each({
    focus: "focusin",
    blur: "focusout"
  }, function (a, b) {
    function d(e) {
      e = c.event.fix(e);
      e.type = b;
      return c.event.handle.call(this, e)
    }
    c.event.special[b] = {
      setup: function () {
        this.addEventListener(a, d, true)
      },
      teardown: function () {
        this.removeEventListener(a, d, true)
      },
      handler: function (e) {
        arguments[0] = c.event.fix(e);
        arguments[0].type = b;
        return c.event.handle.apply(this, arguments)
      }
    }
  });
  c.extend(c.fn, {
    validateDelegate: function (a, b, d) {
      return this.bind(b, function (e) {
        var f = c(e.target);
        if (f.is(a)) return d.apply(f, arguments)
      })
    }
  })
})(jQuery);
var DateFormat = function (pattern) {
    this._init(pattern)
    };
DateFormat.prototype = {
  _init: function (pattern) {
    this.pattern = pattern;
    this._patterns = [];
    for (var i = 0; i < pattern.length; i++) {
      var ch = pattern.charAt(i);
      if (this._patterns.length == 0) {
        this._patterns[0] = ch
      }
      else {
        var index = this._patterns.length - 1;
        if (this._patterns[index].charAt(0) == "'") {
          if (this._patterns[index].length == 1 || this._patterns[index].charAt(this._patterns[index].length - 1) != "'") {
            this._patterns[index] += ch
          }
          else {
            this._patterns[index + 1] = ch
          }
        }
        else if (this._patterns[index].charAt(0) == ch) {
          this._patterns[index] += ch
        }
        else {
          this._patterns[index + 1] = ch
        }
      }
    }
  },
  format: function (date) {
    var result = [];
    for (var i = 0; i < this._patterns.length; i++) {
      result[i] = this._formatWord(date, this._patterns[i])
    }
    return result.join('')
  },
  _formatWord: function (date, pattern) {
    var formatter = this._formatter[pattern.charAt(0)];
    if (formatter) {
      return formatter.apply(this, [date, pattern])
    }
    else {
      return pattern
    }
  },
  _formatter: {
    "y": function (date, pattern) {
      var year = String(date.getFullYear());
      if (pattern.length <= 2) {
        year = year.substring(2, 4)
      }
      else {
        year = this._zeroPadding(year, pattern.length)
      }
      return year
    },
    "M": function (date, pattern) {
      return this._zeroPadding(String(date.getMonth() + 1), pattern.length)
    },
    "d": function (date, pattern) {
      return this._zeroPadding(String(date.getDate()), pattern.length)
    },
    "H": function (date, pattern) {
      return this._zeroPadding(String(date.getHours()), pattern.length)
    },
    "m": function (date, pattern) {
      return this._zeroPadding(String(date.getMinutes()), pattern.length)
    },
    "s": function (date, pattern) {
      return this._zeroPadding(String(date.getSeconds()), pattern.length)
    },
    "S": function (date, pattern) {
      return this._zeroPadding(String(date.getMilliseconds()), pattern.length)
    },
    "'": function (date, pattern) {
      if (pattern == "''") {
        return "'"
      }
      else {
        return pattern.replace(/'/g, '')
      }
    }
  },
  _zeroPadding: function (str, length) {
    if (str.length >= length) {
      return str
    }
    return new Array(length - str.length + 1).join("0") + str
  },
  parse: function (text) {
    if (typeof text != 'string' || text == '') return null;
    var result = {
      year: 1970,
      month: 1,
      day: 1,
      hour: 0,
      min: 0,
      sec: 0,
      msec: 0
    };
    for (var i = 0; i < this._patterns.length; i++) {
      if (text == '') return null;
      text = this._parseWord(text, this._patterns[i], result);
      if (text === null) return null
    }
    if (text != '') return null;
    return new Date(result.year, result.month - 1, result.day, result.hour, result.min, result.sec, result.msec)
  },
  _parseWord: function (text, pattern, result) {
    var parser = this._parser[pattern.charAt(0)];
    if (parser) {
      return parser.apply(this, [text, pattern, result])
    }
    else {
      if (text.indexOf(pattern) != 0) {
        return null
      }
      else {
        return text.substring(pattern.length)
      }
    }
  },
  _parser: {
    "y": function (text, pattern, result) {
      var year;
      if (pattern.length <= 2) {
        year = text.substring(0, 2);
        year = year < 70 ? '20' + year : '19' + year;
        text = text.substring(2)
      }
      else {
        var length = (pattern.length == 3) ? 4 : pattern.length;
        year = text.substring(0, length);
        text = text.substring(length)
      }
      if (!this._isNumber(year)) return null;
      result.year = parseInt(year, 10);
      return text
    },
    "M": function (text, pattern, result) {
      var month;
      if (pattern.length == 1 && text.length > 1 && text.substring(0, 2).match(/1[0-2]/) != null) {
        month = text.substring(0, 2);
        text = text.substring(2)
      }
      else {
        month = text.substring(0, pattern.length);
        text = text.substring(pattern.length)
      }
      if (!this._isNumber(month)) return null;
      result.month = parseInt(month, 10);
      return text
    },
    "d": function (text, pattern, result) {
      var day;
      if (pattern.length == 1 && text.length > 1 && text.substring(0, 2).match(/1[0-9]|2[0-9]|3[0-1]/) != null) {
        day = text.substring(0, 2);
        text = text.substring(2)
      }
      else {
        day = text.substring(0, pattern.length);
        text = text.substring(pattern.length)
      }
      if (!this._isNumber(day)) return null;
      result.day = parseInt(day, 10);
      return text
    },
    "H": function (text, pattern, result) {
      var hour;
      if (pattern.length == 1 && text.length > 1 && text.substring(0, 2).match(/1[0-9]|2[0-3]/) != null) {
        hour = text.substring(0, 2);
        text = text.substring(2)
      }
      else {
        hour = text.substring(0, pattern.length);
        text = text.substring(pattern.length)
      }
      if (!this._isNumber(hour)) return null;
      result.hour = parseInt(hour, 10);
      return text
    },
    "m": function (text, pattern, result) {
      var min;
      if (pattern.length == 1 && text.length > 1 && text.substring(0, 2).match(/[1-5][0-9]/) != null) {
        min = text.substring(0, 2);
        text = text.substring(2)
      }
      else {
        min = text.substring(0, pattern.length);
        text = text.substring(pattern.length)
      }
      if (!this._isNumber(min)) return null;
      result.min = parseInt(min, 10);
      return text
    },
    "s": function (text, pattern, result) {
      var sec;
      if (pattern.length == 1 && text.length > 1 && text.substring(0, 2).match(/[1-5][0-9]/) != null) {
        sec = text.substring(0, 2);
        text = text.substring(2)
      }
      else {
        sec = text.substring(0, pattern.length);
        text = text.substring(pattern.length)
      }
      if (!this._isNumber(sec)) return null;
      result.sec = parseInt(sec, 10);
      return text
    },
    "S": function (text, pattern, result) {
      var msec;
      if (pattern.length == 1 || pattern.length == 2) {
        if (text.length > 2 && text.substring(0, 3).match(/[1-9][0-9][0-9]/) != null) {
          msec = text.substring(0, 3);
          text = text.substring(3)
        }
        else if (text.length > 1 && text.substring(0, 2).match(/[1-9][0-9]/) != null) {
          msec = text.substring(0, 2);
          text = text.substring(2)
        }
        else {
          msec = text.substring(0, pattern.length);
          text = text.substring(pattern.length)
        }
      }
      else {
        msec = text.substring(0, pattern.length);
        text = text.substring(pattern.length)
      }
      if (!this._isNumber(msec)) return null;
      result.msec = parseInt(msec, 10);
      return text
    },
    "'": function (text, pattern, result) {
      if (pattern == "''") {
        pattern = "'"
      }
      else {
        pattern = pattern.replace(/'/g, '')
      }
      if (text.indexOf(pattern) != 0) {
        return null
      }
      else {
        return text.substring(pattern.length)
      }
    }
  },
  _isNumber: function (str) {
    return /^[0-9]*$/.test(str)
  }
}
var forwardFunc;
(function ($) {
  $.movingBoxes = function (el, options) {
    var base = this;
    base.$el = $(el).addClass('mb-slider');
    base.el = el;
    base.$el.data('movingBoxes', base);
    base.init = function () {
      base.options = $.extend({}, $.movingBoxes.defaultOptions, options);
      base.$el.wrap('<div class="movingBoxes mb-wrapper"><div class="mb-scroll" /></div>');
      base.$window = base.$el.parent();
      base.$wrap = base.$window.parent().css({
        width: base.options.width
      }).prepend('<a class="mb-scrollButtons mb-left"></a>').append('<a class="mb-scrollButtons mb-right"></a><div class="mb-left-shadow"></div><div class="mb-right-shadow"></div>');
      base.$panels = base.$el.find(base.options.panelType).addClass('mb-panel');
      base.runTime = $('.mb-slider').index(base.$el) + 1;
      base.regex = new RegExp('slider' + base.runTime + '=(\\d+)', 'i');
      base.initialized = false;
      base.currentlyMoving = false;
      base.curPanel = 1;
      base.update();
      base.$wrap.find('.mb-right').click(function () {
        base.goForward();
        return false
      }).end().find('.mb-left').click(function () {
        base.goBack();
        return false
      });
      base.$el.delegate('.mb-panel', 'click', function () {
        base.change(base.$panels.index($(this)) + 1)
      });
      base.$wrap.click(function () {
        base.active()
      });
      base.$panels.delegate('a', 'focus', function () {
        var loc = base.$panels.index($(this).closest('.mb-panel')) + 1;
        if (loc !== base.curPanel) {
          base.change(base.$panels.index($(this).closest('.mb-panel')) + 1, {}, false)
        }
      });
      $(document).keyup(function (e) {
        if (e.target.tagName.match('TEXTAREA|INPUT|SELECT')) {
          return
        }
        switch (e.which) {
        case 39:
        case 32:
          if (base.$wrap.is('.mb-active-slider')) {
            base.goForward()
          }
          break;
        case 37:
          if (base.$wrap.is('.mb-active-slider')) {
            base.goBack()
          }
          break
        }
      });
      var startPanel = (base.options.hashTags) ? base.getHash() || base.options.startPanel : base.options.startPanel;
      setTimeout(function () {
        base.change(startPanel, function () {
          $.each('initialized.movingBoxes initChange.movingBoxes beforeAnimation.movingBoxes completed.movingBoxes'.split(' '), function (i, o) {
            var evt = o.split('.')[0];
            if ($.isFunction(base.options[evt])) {
              base.$el.bind(o, base.options[evt])
            }
          });
          base.initialized = true;
          base.$el.trigger('initialized.movingBoxes', [base, startPanel])
        })
      }, base.options.speed * 2)
    };
    base.update = function () {
      var t;
      base.$panels = base.$el.find(base.options.panelType).addClass('mb-panel').css({
        width: base.options.width * base.options.panelWidth
      }).each(function () {
        if ($(this).find('.mb-inside').length === 0) {
          $(this).wrapInner('<div class="mb-inside" />')
        }
      });
      base.totalPanels = base.$panels.length;
      t = base.$panels.eq(base.curPanel - 1);
      base.curWidth = base.curWidth || t.outerWidth();
      base.curImgWidth = base.curImgWidth || t.find('img').outerWidth(true);
      base.curImgHeight = base.curImgHeight || base.curImgWidth / base.options.imageRatio;
      base.regWidth = base.curWidth * base.options.reducedSize;
      base.regImgWidth = base.curImgWidth * base.options.reducedSize;
      base.regImgHeight = base.curImgHeight * base.options.reducedSize;
      base.$panels.css({
        width: base.curWidth,
        fontSize: '1em'
      }).find('img').css({
        height: base.curImgHeight,
        width: base.curImgWidth
      });
      base.heights = base.$panels.map(function (i, e) {
        return $(e).outerHeight(true)
      }).get();
      base.returnToNormal(base.curPanel, true);
      base.growBigger(base.curPanel, true);
      base.$el.css({
        position: 'absolute',
        width: (base.curWidth + 100) * base.totalPanels + (base.options.width - base.curWidth) / 2,
        height: Math.max.apply(this, base.heights) + 10
      });
      base.$window.css({
        height: (base.options.fixedHeight) ? Math.max.apply(this, base.heights) : base.heights[base.curPanel - 1]
      });
      base.$panels.eq(0).css({
        'margin-left': (base.options.width - base.curWidth) / 2
      });
      base.buildNav();
      base.change(base.curPanel, {}, false)
    };
    base.buildNav = function () {
      base.$navLinks = {};
      if (base.$nav) {
        base.$nav.remove()
      }
      if (base.options.buildNav && (base.totalPanels > 1)) {
        base.$nav = $('<div class="mb-controls"><a class="mb-testing"></a></div>').appendTo(base.$wrap);
        var j, a = '',
            navFormat = $.isFunction(base.options.navFormatter),
            hiddenText = parseInt(base.$nav.find('.mb-testing').css('text-indent'), 10) < 0;
        base.$panels.each(function (i) {
          j = i + 1;
          a += '<a href="#" class="mb-panel' + j;
          if (navFormat) {
            var tmp = base.options.navFormatter(j, $(this));
            a += (hiddenText) ? ' ' + base.options.tooltipClass + '" title="' + tmp : '';
            a += '">' + tmp + '</a> '
          }
          else {
            a += '">' + j + '</a> '
          }
        });
        base.$navLinks = base.$nav.html(a).find('a').bind('click', function () {
          base.change(base.$navLinks.index($(this)) + 1);
          return false
        })
      }
    };
    base.returnToNormal = function (num, quick) {
      base.$panels.not(':eq(' + (num - 1) + ')').removeClass(base.options.currentPanel).animate({
        width: base.regWidth,
        fontSize: base.options.reducedSize + 'em'
      }, (quick) ? 0 : base.options.speed).find('img').animate({
        width: base.regImgWidth,
        height: base.regImgHeight
      }, (quick) ? 0 : base.options.speed)
    };
    base.growBigger = function (num, quick) {
      base.$panels.eq(num - 1).addClass(base.options.currentPanel).animate({
        width: base.curWidth,
        fontSize: '1em'
      }, (quick) ? 0 : base.options.speed).find('img').animate({
        width: base.curImgWidth,
        height: base.curImgHeight
      }, (quick) ? 0 : base.options.speed, function () {
        if (base.initialized) {
          base.$el.trigger('completed.movingBoxes', [base, num])
        }
      })
    };
    base.goForward = function () {
      base.change(base.curPanel + 1)
    };
    forwardFunc = base.goForward;
    base.goBack = function () {
      base.change(base.curPanel - 1)
    };
    base.change = function (curPanel, callback, flag) {
      curPanel = parseInt(curPanel, 10);
      if (base.initialized) {
        base.active();
        base.$el.trigger('initChange.movingBoxes', [base, curPanel])
      }
      if (base.options.wrap) {
        if (curPanel < 1) {
          curPanel = base.totalPanels
        }
        if (curPanel > base.totalPanels) {
          curPanel = 1
        }
      }
      else {
        if (curPanel < 1) {
          curPanel = 1
        }
        if (curPanel > base.totalPanels) {
          curPanel = base.totalPanels
        }
      }
      if (base.initialized && base.curPanel == curPanel && !flag) {
        return false
      }
      if (!base.currentlyMoving) {
        base.currentlyMoving = true;
        var ani, leftValue = base.$panels.eq(curPanel - 1).position().left - (base.options.width - base.curWidth) / 2;
        if (curPanel > base.curPanel) {
          leftValue -= (base.curWidth - base.regWidth)
        }
        ani = (base.options.fixedHeight) ? {
          scrollLeft: leftValue
        } : {
          scrollLeft: leftValue,
          height: base.heights[curPanel - 1]
        };
        if (base.initialized) {
          base.$el.trigger('beforeAnimation.movingBoxes', [base, curPanel])
        }
        base.$window.animate(ani, {
          queue: false,
          duration: base.options.speed,
          easing: base.options.easing,
          complete: function () {
            base.curPanel = curPanel;
            if (base.initialized) {
              base.$window.scrollTop(0)
            }
            base.currentlyMoving = false;
            if (typeof (callback) === 'function') {
              callback(base)
            }
          }
        });
        base.returnToNormal(curPanel);
        base.growBigger(curPanel);
        if (base.options.hashTags) {
          base.setHash(curPanel)
        }
      }
      base.$wrap.find('.mb-controls a').removeClass(base.options.currentPanel).eq(curPanel - 1).addClass(base.options.currentPanel)
    };
    base.getHash = function () {
      var n = window.location.hash.match(base.regex);
      return (n === null) ? '' : parseInt(n[1], 10)
    };
    base.setHash = function (n) {
      var s = 'slider' + base.runTime + "=",
          h = window.location.hash;
      if (typeof h !== 'undefined') {
        window.location.hash = (h.indexOf(s) > 0) ? h.replace(base.regex, s + n) : h + "&" + s + n
      }
    };
    base.active = function (el) {
      $('.mb-active-slider').removeClass('mb-active-slider');
      base.$wrap.addClass('mb-active-slider')
    };
    base.currentPanel = function (panel, callback) {
      if (typeof (panel) !== 'undefined') {
        base.change(panel, callback)
      }
      return base.curPanel
    };
    base.init()
  };
  $.movingBoxes.defaultOptions = {
    startPanel: 1,
    width: 800,
    panelWidth: 0.5,
    reducedSize: 0.8,
    imageRatio: 4 / 3,
    fixedHeight: false,
    speed: 500,
    hashTags: true,
    wrap: false,
    buildNav: false,
    navFormatter: null,
    easing: 'swing',
    currentPanel: 'current',
    tooltipClass: 'tooltip',
    panelType: '> div',
    initialized: null,
    initChange: null,
    beforeAnimation: null,
    completed: null
  };
  $.fn.movingBoxes = function (options, callback) {
    var num, mb = this.data('movingBoxes');
    return this.each(function () {
      if ((typeof (options)).match('object|undefined')) {
        if (mb) {
          mb.update()
        }
        else {
          (new $.movingBoxes(this, options))
        }
      }
      else if (/\d/.test(options) && !isNaN(options) && mb) {
        num = (typeof (options) === "number") ? options : parseInt($.trim(options), 10);
        if (num >= 1 && num <= mb.totalPanels) {
          mb.change(num, callback)
        }
      }
    })
  };
  $.fn.getMovingBoxes = function () {
    return this.data('movingBoxes')
  }
})(jQuery);

function initMovingBoxes(initCallback, completedCallback) {
  $('#slider').movingBoxes({
    startPanel: 2,
    width: 700,
    panelWidth: .3,
    hashTags: false,
    fixedHeight: true,
    imageRatio: 1 / 1,
    buildNav: true,
    navFormatter: function () {
      return "&#9679;"
    },
    wrap: true,
    initialized: initCallback,
    completed: completedCallback,
  })
}
function _stripScripts(scriptsString) {
  var ScriptFragment = '<script[^>]*>([\\S\\s]*?)<\/script>';
  return scriptsString.replace(new RegExp(ScriptFragment, 'img'), '')
}
function _extractScripts(scriptsString) {
  var ScriptFragment = '<script[^>]*>([\\S\\s]*?)<\/script>';
  var matchAll = new RegExp(ScriptFragment, 'img');
  var matchOne = new RegExp(ScriptFragment, 'im');
  return jQuery.map(scriptsString.match(matchAll) || [], function (scriptTag) {
    return (scriptTag.match(matchOne) || ['', ''])[1]
  })
}
function _evalScripts(scriptsString) {
  jQuery.map(_extractScripts(scriptsString), function (script) {
    jQuery.globalEval(script);
    return true
  })
}
function isIE7() {
  return (typeof document.documentElement.style.msInterpolationMode != 'undefined')
}
function isMac() {
  var agent = navigator.userAgent.toLowerCase();
  return agent.indexOf("macintosh") != -1 || agent.indexOf("mac os") != -1
}
function isChrome() {
  var agent = navigator.userAgent.toLowerCase();
  return agent.indexOf("chrome") != -1
}
jQuery.fn.extend({
  super_load: function (url, params, callback) {
    callback = callback ||
    function () {};
    var type = "GET";
    if (params) if (jQuery.isFunction(params)) {
      callback = params;
      params = null
    }
    else {
      params = jQuery.param(params);
      type = "POST"
    }
    var self = this;
    jQuery.ajax({
      url: url,
      type: type,
      dataType: "html",
      data: params,
      complete: function (res, status) {
        if (status == "success" || status == "notmodified") {
          if ((jQuery.browser.msie && !isIE7()) || (jQuery.browser.safari && isMac()) || (isChrome())) {
            self.html(_stripScripts(res.responseText));
            _evalScripts(res.responseText)
          }
          else {
            self.html(res.responseText)
          }
        }
        self.each(callback, [res.responseText, status, res])
      }
    });
    return this
  }
});
(function ($) {
  $.fn.createVideo = function (config) {
    var defaults = {
      width: 315,
      height: 192
    }
    var options = $.extend(defaults, config);
    return this.each(function () {
      var o = options;
      var videoId;
      var type;
      if (!(type = $(this).attr("rel"))) return;
      if (type == "video") {
        var url = $(this).attr("href");
        if (url.indexOf("http://www.youtube.com/") != -1 || url.indexOf("http://youtube.com/") != -1) type = "youtube";
        else if (url.indexOf("http://www.dailymotion.com") != -1 || url.indexOf("http://dailymotion.com") != -1) type = "dailymotion";
        else if (url.indexOf("http://www.vimeo.com/") != -1 || url.indexOf("http://vimeo.com/") != -1) type = "vimeo";
        else if (url.indexOf("http://www.nicovideo.jp/") != -1 || url.indexOf("http://nicovideo.jp/") != -1) type = "nicovideo"
      }
      switch (type) {
      case "youtube":
        var str = String($(this).attr("href").match(/(\?|&)v=.*/g));
        str = str.substr(3);
        if (str.lastIndexOf("&") == -1) {
          videoId = str
        }
        else {
          var endpos = str.lastIndexOf("&");
          videoId = str.substr(0, endpos)
        }
        $(this).after('<div class="videoContainer"><iframe width="' + o.width + '" height="' + o.height + '" src="http://www.youtube.com/embed/' + videoId + '?wmode=transparent" frameborder="0" allowfullscreen></iframe></div>');
        $(this).remove();
        break;
      case "dailymotion":
        var str = String($(this).attr("href").match(/\/video\/.*/g));
        str = str.substr(7);
        if (str.indexOf("_") == -1) {
          videoId = str
        }
        else {
          var endpos = str.indexOf("_");
          videoId = str.substr(0, endpos)
        }
        $(this).after('<div class="videoContainer"><iframe frameborder="0" width="' + o.width + '" height="' + o.height + '" src="http://www.dailymotion.com/embed/video/' + videoId + '?width=' + o.width + '&theme=none&wmode=opaque"></iframe></div>');
        $(this).remove();
        break;
      case "vimeo":
        var str = String($(this).attr("href"));
        videoId = str = str.substr(str.lastIndexOf("/") + 1); $(this).after('<div class="videoContainer"><iframe src="http://player.vimeo.com/video/' + videoId + '?portrait=0" width="' + o.width + '" height="' + o.height + '" frameborder="0"></iframe></div>');
        $(this).remove();
        break;
      case "nicovideo":
        var str = String($(this).attr("href"));
        videoId = str = str.substr(str.lastIndexOf("/") + 1).split("?")[0];
        var $div = $('<div class="videoContainer"></div>');
        $(this).after($div);
        $(this).remove();
        var $ifm = $div.append('<iframe frameborder="0" width="' + o.width + '" height="' + o.height + '" />').find('> :last-child');
        var $idoc = $ifm.contents();
        $idoc[0].open();
        $idoc[0].write('<html><body style="margin:0; padding:0; background-color:#000;"><div style="margin:0; padding:0;"><script type="text/javascript" src="http://ext.nicovideo.jp/thumb_watch/' + videoId + '?w=' + o.width + '&h=' + o.height + '&tr=1"></script></div></body></html>');
        $ifm.bind('load', function () {
          $(window).unload = function () {};
          window.location = url
        });
        break;
      default:
        break
      }
    })
  }
})(jQuery);
(function ($) {
  $.fn.charCount = function (options) {
    var defaults = {
      allowed: 128,
      warning: 25,
      css: 'counter',
      counterElement: 'div',
      cssWarning: 'warning',
      cssExceeded: 'exceeded',
      counterText: ''
    };
    var options = $.extend(defaults, options);

    function calculate(obj) {
      var count = $(obj).val().length;
      var available = options.allowed - count;
      if (available <= options.warning && available >= 0) {
        $(obj).next().addClass(options.cssWarning)
      }
      else {
        $(obj).next().removeClass(options.cssWarning)
      }
      if (available < 0) {
        $(obj).next().addClass(options.cssExceeded)
      }
      else {
        $(obj).next().removeClass(options.cssExceeded)
      }
      $(obj).next().html(options.counterText + available)
    };
    this.each(function () {
      $(this).after('<' + options.counterElement + ' style="display:inline-block;margin-left:10px;" class="">' + options.counterText + '</' + options.counterElement + '>');
      calculate(this);
      $(this).keyup(function () {
        calculate(this)
      });
      $(this).change(function () {
        calculate(this)
      })
    })
  }
})(jQuery);
(function ($) {
	  $.extend({
	    _jsonp: {
	      scripts: {},
	      charset: 'utf-8',
	      counter: 1,
	      head: document.getElementsByTagName("head")[0],
	      name: function (callback) {
	        var name = '_jsonp_' + (new Date).getTime() + '_' + this.counter;
	        this.counter++;
	        var cb = function (json) {
	            eval('delete ' + name);
	            callback(json);
	            $._jsonp.head.removeChild($._jsonp.scripts[name]);
	            delete $._jsonp.scripts[name]
	            };
	        eval(name + ' = cb');
	        return name
	      },
	      load: function (url, name) {
	        var script = document.createElement('script');
	        script.type = 'text/javascript';
	        script.charset = this.charset;
	        script.src = url;
	        this.head.appendChild(script);
	        this.scripts[name] = script;
	      },
	      load2: function (jso, name) {
		        var script = document.createElement('script');
		        script.type = 'text/javascript';
		        script.charset = this.charset;
		        script.innerHTML  = jso;
		        this.head.appendChild(script);
		        this.scripts[name] = script;
		      }
	    },
	    getJSONP: function (url, callback) {
	      var name = $._jsonp.name(callback);
	      var url = url.replace(/{callback}/, name);
	        $._jsonp.load(url, name);
	      return this
	    }
	  })
	})(jQuery);
(function ($) {
  $.timeago = function (timestamp) {
    if (timestamp instanceof Date) {
      return inWords(timestamp)
    }
    else if (typeof timestamp === "string") {
      return inWords($.timeago.parse(timestamp))
    }
    else {
      return inWords($.timeago.datetime(timestamp))
    }
  };
  var $t = $.timeago;
  $.extend($.timeago, {
    settings: {
      refreshMillis: 60000,
      allowFuture: false,
      strings: {
        prefixAgo: null,
        prefixFromNow: null,
        suffixAgo: "ago",
        suffixFromNow: "from now",
        seconds: "less than a minute",
        minute: "about a minute",
        minutes: "%d minutes",
        hour: "about an hour",
        hours: "about %d hours",
        day: "a day",
        days: "%d days",
        month: "about a month",
        months: "%d months",
        year: "about a year",
        years: "%d years",
        numbers: []
      }
    },
    inWords: function (distanceMillis) {
      var $l = this.settings.strings;
      var prefix = $l.prefixAgo;
      var suffix = $l.suffixAgo;
      if (this.settings.allowFuture) {
        if (distanceMillis < 0) {
          prefix = $l.prefixFromNow;
          suffix = $l.suffixFromNow
        }
        distanceMillis = Math.abs(distanceMillis)
      }
      var seconds = distanceMillis / 1000;
      var minutes = seconds / 60;
      var hours = minutes / 60;
      var days = hours / 24;
      var years = days / 365;

      function substitute(stringOrFunction, number) {
        var string = $.isFunction(stringOrFunction) ? stringOrFunction(number, distanceMillis) : stringOrFunction;
        var value = ($l.numbers && $l.numbers[number]) || number;
        return string.replace(/%d/i, value)
      }
      var words = seconds < 45 && substitute($l.seconds, Math.round(seconds)) || seconds < 90 && substitute($l.minute, 1) || minutes < 45 && substitute($l.minutes, Math.round(minutes)) || minutes < 90 && substitute($l.hour, 1) || hours < 24 && substitute($l.hours, Math.round(hours)) || hours < 48 && substitute($l.day, 1) || days < 30 && substitute($l.days, Math.floor(days)) || days < 60 && substitute($l.month, 1) || days < 365 && substitute($l.months, Math.floor(days / 30)) || years < 2 && substitute($l.year, 1) || substitute($l.years, Math.floor(years));
      return $.trim([prefix, words, suffix].join(" "))
    },
    parse: function (iso8601) {
      var s = $.trim(iso8601);
      s = s.replace(/\.\d\d\d+/, "");
      s = s.replace(/-/, "/").replace(/-/, "/");
      s = s.replace(/T/, " ").replace(/Z/, " UTC");
      s = s.replace(/([\+\-]\d\d)\:?(\d\d)/, " $1$2");
      return new Date(s)
    },
    datetime: function (elem) {
      var isTime = $(elem).get(0).tagName.toLowerCase() === "time";
      var iso8601 = isTime ? $(elem).attr("datetime") : $(elem).attr("title");
      return $t.parse(iso8601)
    }
  });
  $.fn.timeago = function () {
    var self = this;
    self.each(refresh);
    var $s = $t.settings;
    if ($s.refreshMillis > 0) {
      setInterval(function () {
        self.each(refresh)
      }, $s.refreshMillis)
    }
    return self
  };

  function refresh() {
    var data = prepareData(this);
    if (!isNaN(data.datetime)) {
      $(this).text(inWords(data.datetime))
    }
    return this
  }
  function prepareData(element) {
    element = $(element);
    if (!element.data("timeago")) {
      element.data("timeago", {
        datetime: $t.datetime(element)
      });
      var text = $.trim(element.text());
      if (text.length > 0) {
        element.attr("title", text)
      }
    }
    return element.data("timeago")
  }
  function inWords(date) {
    return $t.inWords(distance(date))
  }
  function distance(date) {
    return (new Date().getTime() - date.getTime())
  }
  document.createElement("abbr");
  document.createElement("time")
}(jQuery));
jQuery.notifyBar = function (settings) {
  (function ($) {
    var bar = notifyBarNS = {};
    notifyBarNS.shown = false;
    if (!settings) {
      settings = {}
    }
    notifyBarNS.html = settings.html || "Your message here";
    notifyBarNS.delay = settings.delay || 2000;
    notifyBarNS.animationSpeed = settings.animationSpeed || 200;
    notifyBarNS.jqObject = settings.jqObject;
    notifyBarNS.cls = settings.cls || "";
    notifyBarNS.close = settings.close || false;
    if (notifyBarNS.jqObject) {
      bar = notifyBarNS.jqObject;
      notifyBarNS.html = bar.html()
    }
    else {
      bar = jQuery("<div></div>").addClass("jquery-notify-bar").addClass(notifyBarNS.cls).attr("id", "__notifyBar")
    }
    bar.html(notifyBarNS.html).hide();
    var id = bar.attr("id");
    switch (notifyBarNS.animationSpeed) {
    case "slow":
      asTime = 600;
      break;
    case "normal":
      asTime = 400;
      break;
    case "fast":
      asTime = 200;
      break;
    default:
      asTime = notifyBarNS.animationSpeed
    }
    if (bar != 'object'); {
      jQuery("body").prepend(bar)
    }
    if (notifyBarNS.close) {
      bar.append(jQuery("<a href='#' class='notify-bar-close'>Close [X]</a>"));
      jQuery(".notify-bar-close").click(function () {
        if (bar.attr("id") == "__notifyBar") {
          jQuery("#" + id).slideUp(asTime, function () {
            jQuery("#" + id).remove()
          })
        }
        else {
          jQuery("#" + id).slideUp(asTime)
        }
        return false
      })
    }
    if ($('.jquery-notify-bar:visible').length > 0) {
      $('.jquery-notify-bar:visible').stop().slideUp(asTime, function () {
        bar.stop().slideDown(asTime)
      })
    }
    else {
      bar.slideDown(asTime)
    }
    bar.click(function () {
      $(this).slideUp(asTime)
    }); if (bar.attr("id") == "__notifyBar") {
      setTimeout("jQuery('#" + id + "').stop().slideUp(" + asTime + ", function() {jQuery('#" + id + "').remove()});", notifyBarNS.delay + asTime)
    }
    else {
      setTimeout("jQuery('#" + id + "').stop().slideUp(" + asTime + ", function() {jQuery('#" + id + "')});", notifyBarNS.delay + asTime)
    }
  })(jQuery)
};
(function ($) {
  $.PaginationCalculator = function (maxentries, opts) {
    this.maxentries = maxentries;
    this.opts = opts
  }
  $.extend($.PaginationCalculator.prototype, {
    numPages: function () {
      return Math.ceil(this.maxentries / this.opts.items_per_page)
    },
    getInterval: function (current_page) {
      var ne_half = Math.floor(this.opts.num_display_entries / 2);
      var np = this.numPages();
      var upper_limit = np - this.opts.num_display_entries;
      var start = current_page > ne_half ? Math.max(Math.min(current_page - ne_half, upper_limit), 0) : 0;
      var end = current_page > ne_half ? Math.min(current_page + ne_half + (this.opts.num_display_entries % 2), np) : Math.min(this.opts.num_display_entries, np);
      return {
        start: start,
        end: end
      }
    }
  });
  $.PaginationRenderers = {}
  $.PaginationRenderers.defaultRenderer = function (maxentries, opts) {
    this.maxentries = maxentries;
    this.opts = opts;
    this.pc = new $.PaginationCalculator(maxentries, opts)
  }
  $.extend($.PaginationRenderers.defaultRenderer.prototype, {
    createLink: function (page_id, current_page, appendopts) {
      var lnk, np = this.pc.numPages();
      page_id = page_id < 0 ? 0 : (page_id < np ? page_id : np - 1);
      appendopts = $.extend({
        text: page_id + 1,
        classes: ""
      }, appendopts || {});
      if (page_id == current_page) {
        lnk = $("<span class='current' style='margin-left:3px;margin-right:3px;'>" + appendopts.text + "</span>")
      }
      else {
        lnk = $("<a style='margin-left:3px;margin-right:3px;'>" + appendopts.text + "</a>").attr('href', this.opts.link_to.replace(/__id__/, page_id))
      }
      if (appendopts.classes) {
        lnk.addClass(appendopts.classes)
      }
      lnk.data('page_id', page_id);
      return lnk
    },
    appendRange: function (container, current_page, start, end, opts) {
      var i;
      for (i = start; i < end; i++) {
        this.createLink(i, current_page, opts).appendTo(container)
      }
    },
    getLinks: function (current_page, eventHandler) {
      var begin, end, interval = this.pc.getInterval(current_page),
          np = this.pc.numPages(),
          fragment = $("<div class='pagination'></div>");
      if (this.opts.prev_text && (current_page > 0 || this.opts.prev_show_always)) {
        fragment.append(this.createLink(current_page - 1, current_page, {
          text: this.opts.prev_text,
          classes: "prev"
        }))
      }
      if (interval.start > 0 && this.opts.num_edge_entries > 0) {
        end = Math.min(this.opts.num_edge_entries, interval.start);
        this.appendRange(fragment, current_page, 0, end, {
          classes: 'sp'
        });
        if (this.opts.num_edge_entries < interval.start && this.opts.ellipse_text) {
          jQuery("<span>" + this.opts.ellipse_text + "</span>").appendTo(fragment)
        }
      }
      this.appendRange(fragment, current_page, interval.start, interval.end);
      if (interval.end < np && this.opts.num_edge_entries > 0) {
        if (np - this.opts.num_edge_entries > interval.end && this.opts.ellipse_text) {
          jQuery("<span>" + this.opts.ellipse_text + "</span>").appendTo(fragment)
        }
        begin = Math.max(np - this.opts.num_edge_entries, interval.end);
        this.appendRange(fragment, current_page, begin, np, {
          classes: 'ep'
        })
      }
      if (this.opts.next_text && (current_page < np - 1 || this.opts.next_show_always)) {
        fragment.append(this.createLink(current_page + 1, current_page, {
          text: this.opts.next_text,
          classes: "next"
        }))
      }
      $('a', fragment).click(eventHandler);
      return fragment
    }
  });
  $.fn.pagination = function (maxentries, opts) {
    opts = jQuery.extend({
      items_per_page: 10,
      num_display_entries: 11,
      current_page: 0,
      num_edge_entries: 0,
      link_to: "#",
      prev_text: "Prev",
      next_text: "Next",
      ellipse_text: "...",
      prev_show_always: true,
      next_show_always: true,
      renderer: "defaultRenderer",
      callback: function () {
        return false
      }
    }, opts || {});
    var containers = this,
        renderer, links, current_page;

    function paginationClickHandler(evt) {
      var links, new_current_page = $(evt.target).data('page_id'),
          continuePropagation = selectPage(new_current_page);
      if (!continuePropagation) {
        evt.stopPropagation()
      }
      return continuePropagation
    }
    function selectPage(new_current_page) {
      containers.data('current_page', new_current_page);
      links = renderer.getLinks(new_current_page, paginationClickHandler);
      containers.empty();
      links.appendTo(containers);
      var continuePropagation = opts.callback(new_current_page, containers);
      return continuePropagation
    }
    current_page = opts.current_page;
    containers.data('current_page', current_page);
    maxentries = (!maxentries || maxentries < 0) ? 1 : maxentries;
    opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0) ? 1 : opts.items_per_page;
    if (!$.PaginationRenderers[opts.renderer]) {
      throw new ReferenceError("Pagination renderer '" + opts.renderer + "' was not found in jQuery.PaginationRenderers object.");
    }
    renderer = new $.PaginationRenderers[opts.renderer](maxentries, opts);
    var pc = new $.PaginationCalculator(maxentries, opts);
    var np = pc.numPages();
    containers.bind('setPage', {
      numPages: np
    }, function (evt, page_id) {
      if (page_id >= 0 && page_id < evt.data.numPages) {
        selectPage(page_id);
        return false
      }
    });
    containers.bind('prevPage', function (evt) {
      var current_page = $(this).data('current_page');
      if (current_page > 0) {
        selectPage(current_page - 1)
      }
      return false
    });
    containers.bind('nextPage', {
      numPages: np
    }, function (evt) {
      var current_page = $(this).data('current_page');
      if (current_page < evt.data.numPages - 1) {
        selectPage(current_page + 1)
      }
      return false
    });
    links = renderer.getLinks(current_page, paginationClickHandler);
    containers.empty();
    links.appendTo(containers);
    opts.callback(current_page, containers)
  }
})(jQuery);

(function ($) {
    var globalTags = [];

    // creates a public function within our private code.
    // tags can either be an array of strings OR
    // array of objects containing a 'tag' attribute
    window.setGlobalTags = function(tags /* array */) {
        globalTags = getTags(tags);
    };
    
    function getTags(tags) {
        var tag, i, goodTags = [];
        for (i = 0; i < tags.length; i++) {
            tag = tags[i];
            if (typeof tags[i] == 'object') {
                tag = tags[i].tag;
            } 
            goodTags.push(tag.toLowerCase());
        }
        
        return goodTags;
    }
    
    $.fn.tagSuggest = function (options) {
        var defaults = { 
            'matchClass' : 'tagResults', 
            'tagContainer' : 'span', 
            'tagWrap' : 'div', 
            'sort' : true,
            'tags' : null,
            'url' : null,
            'delay' : 0,
            'separator' : ' '
        };

        var i, tag, userTags = [], settings = $.extend({}, defaults, options);

        if (settings.tags) {
            userTags = getTags(settings.tags);
        } else {
            userTags = globalTags;
        }

        return this.each(function () {
            var tagsElm = $(this);
            var elm = this;
            var matches, fromTab = false;
            var suggestionsShow = false;
            var workingTags = [];
            var currentTag = {"position": 0, tag: ""};
            var tagMatches = document.createElement(settings.tagContainer);
            
            function showSuggestionsDelayed(el, key) {
                if (settings.delay) {
                    if (elm.timer) clearTimeout(elm.timer);
                    elm.timer = setTimeout(function () {
                        showSuggestions(el, key);
                    }, settings.delay);
                } else {
                    showSuggestions(el, key);
                }
            }

            function showSuggestions(el, key) {
                workingTags = el.value.split(settings.separator);
                matches = [];
                var i, html = '', chosenTags = {}, tagSelected = false;

                // we're looking to complete the tag on currentTag.position (to start with)
                currentTag = { position: currentTags.length-1, tag: '' };
                
                for (i = 0; i < currentTags.length && i < workingTags.length; i++) {
                    if (!tagSelected && 
                        currentTags[i].toLowerCase() != workingTags[i].toLowerCase()) {
                        currentTag = { position: i, tag: workingTags[i].toLowerCase() };
                        tagSelected = true;
                    }
                    // lookup for filtering out chosen tags
                    chosenTags[currentTags[i].toLowerCase()] = true;
                }

                if (currentTag.tag) {
                    // collect potential tags
                    if (settings.url) {
                        $.ajax({
                            'url' : settings.url,
                            'dataType' : 'json',
                            'data' : { 'tag' : currentTag.tag },
                            'async' : false, // wait until this is ajax hit is complete before continue
                            'success' : function (m) {
                                matches = m;
                            }
                        });
                    } else {
                        for (i = 0; i < userTags.length; i++) {
                            if (userTags[i].indexOf(currentTag.tag) === 0) {
                                matches.push(userTags[i]);
                            }
                        }                        
                    }
                    
                    matches = $.grep(matches, function (v, i) {
                        return !chosenTags[v.toLowerCase()];
                    });

                    if (settings.sort) {
                        matches = matches.sort();
                    }                    

                    for (i = 0; i < matches.length; i++) {
                        html += '<' + settings.tagWrap + ' class="_tag_suggestion">' + matches[i] + '</' + settings.tagWrap + '>';
                    }

                    tagMatches.html(html);
                    suggestionsShow = !!(matches.length);
                } else {
                    hideSuggestions();
                }
            }

            function hideSuggestions() {
                tagMatches.empty();
                matches = [];
                suggestionsShow = false;
            }

            function setSelection() {
                var v = tagsElm.val();

                // tweak for hintted elements
                // http://remysharp.com/2007/01/25/jquery-tutorial-text-box-hints/
                if (v == tagsElm.attr('title') && tagsElm.is('.hint')) v = '';

                currentTags = v.split(settings.separator);
                hideSuggestions();
            }

            function chooseTag(tag) {
                var i, index;
                for (i = 0; i < currentTags.length; i++) {
                    if (currentTags[i].toLowerCase() != workingTags[i].toLowerCase()) {
                        index = i;
                        break;
                    }
                }

                if (index == workingTags.length - 1) tag = tag + settings.separator;

                workingTags[i] = tag;

                tagsElm.val(workingTags.join(settings.separator));
                tagsElm.blur().focus();
                setSelection();
            }

            function handleKeys(ev) {
                fromTab = false;
                var type = ev.type;
                var resetSelection = false;
                
                switch (ev.keyCode) {
                    case 37: // ignore cases (arrow keys)
                    case 38:
                    case 39:
                    case 40: {
                        hideSuggestions();
                        return true;
                    }
                    case 224:
                    case 17:
                    case 16:
                    case 18: {
                        return true;
                    }

                    case 8: {
                        // delete - hide selections if we're empty
                        if (this.value == '') {
                            hideSuggestions();
                            setSelection();
                            return true;
                        } else {
                            type = 'keyup'; // allow drop through
                            resetSelection = true;
                            showSuggestionsDelayed(this);
                        }
                        break;
                    }

                    case 9: // return and tab
                    case 13: {
                        if (suggestionsShow) {
                            // complete
                            chooseTag(matches[0]);
                            
                            fromTab = true;
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case 27: {
                        hideSuggestions();
                        setSelection();
                        return true;
                    }
                    case 32: {
                        setSelection();
                        return true;
                    }
                }

                if (type == 'keyup') {
                    switch (ev.charCode) {
                        case 9:
                        case 13: {
                            return true;
                        }
                    }

                    if (resetSelection) { 
                        setSelection();
                    }
                    showSuggestionsDelayed(this, ev.charCode);            
                }
            }

            tagsElm.after(tagMatches).keypress(handleKeys).keyup(handleKeys).blur(function () {
                if (fromTab == true || suggestionsShow) { // tweak to support tab selection for Opera & IE
                    fromTab = false;
                    tagsElm.focus();
                }
            });

            // replace with jQuery version
            tagMatches = $(tagMatches).click(function (ev) {
                if (ev.target.nodeName == settings.tagWrap.toUpperCase() && $(ev.target).is('._tag_suggestion')) {
                    chooseTag(ev.target.innerHTML);
                }                
            }).addClass(settings.matchClass).css("border-style","solid").css("border-width","1px").css("z-index","3");
            // initialise
            setSelection();
        });
    };
})(jQuery);