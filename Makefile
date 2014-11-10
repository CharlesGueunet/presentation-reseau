###############################################################################
# name:	    | Makefile
# @uthor:   | Axel <axel.martin@eisti.fr> Martin
# function: | Makefile pour le pdf de la coding night
# language: | French
# licence:  | Free
# more:     | -
###############################################################################


# Variables de compilation
#######################################

PDFOUTPUT = ./presentation.pdf
ALLLATEX = $(shell find . -name "*.tex")
LATEXOPTS = -halt-on-error
GREPARGS = -A 3 "\(Warning\|Error\)"


# Compilation du document
#######################################

all: $(PDFOUTPUT) clean


%.pdf: %.tex $(ALLLATEX)
	pdflatex $(LATEXOPTS) $< | grep $(GREPARGS)
	$(ifneq ("$(wildcard $(@:.tex=.bib))",""), bibtex $(<:.tex=.aux))
	pdflatex -interaction=batchmode $(LATEXOPTS) $<
	pdflatex -interaction=batchmode $(LATEXOPTS) $<


clean:
	rm -f $(shell find . -regextype posix-extended -regex '.*\.(aux|bbl|blg|toc|log|dvi|ps|snm|nav|out)')
